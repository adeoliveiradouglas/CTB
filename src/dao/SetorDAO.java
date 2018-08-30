/*
 * Responsável por acessar os dados do banco
 * Não há métodos de atualização, insert ou de delete pois não há gerenciamento de dados de setor 
 */

package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Setor;

public class SetorDAO extends DAO{
	private final String colunaNome = getNomeTabela() + ".nome",
						 colunaCodigo = getNomeTabela() + ".codigo",
	   		 			 colunaSigla = getNomeTabela() + ".sigla";

	public SetorDAO(){
		super("setor");
	}
	
	public SetorDAO(Connection conexao){
		super("setor", conexao);
	}
	
	public Setor getByCodigo(String codigo){
		iniciaConexaoComBanco();
		
//		monta a query
		setSqlQuery(
//			select * from setor where codigo = "codigoInserido" 
			"select * from " + getNomeTabela() + " where "+ this.colunaCodigo + " = ?"	
		);
		
		Setor s = null;
		
		try {
//			monta o statement
			setStatement( 
//				pega da conexao
				getDbConnection().prepareStatement(
					getSqlQuery()	
				)
			);
			
//			preenche o statement
			getStatement().setString(
				1, 
				codigo
			);
//			executa a query
			setResultado(
				getStatement().executeQuery()
			);
			
			
//			traduz o resultado para um objeto Setor
			while (getResultado().next()){
				s = new Setor(
					getResultado().getString(colunaCodigo),
					getResultado().getString(colunaNome),
					getResultado().getString(colunaSigla)
				);				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			s = null;
		} 
		
		encerraConexaocomBanco();
		return s;
	}
	
	public Setor getBySigla(String sigla){
		iniciaConexaoComBanco();
		
//		monta a query
		setSqlQuery(
//			select * from setor where sigla = "siglaInserida" 
			"select * from " + getNomeTabela() + " where "+ this.colunaSigla + " = ?"	
		);
		
		Setor s = null;

		try {
//			monta o statement
			setStatement( 
//				pega da conexao
				getDbConnection().prepareStatement(
					getSqlQuery()	
				)
			);
			
//			preenche o statement
			getStatement().setString(
				1, 
				sigla
			);
//			executa a query
			setResultado(
				getStatement().executeQuery()
			);
			
//			traduz o resultado para um objeto Setor
			while (getResultado().next()){
				s = new Setor(
					getResultado().getString(colunaCodigo),
					getResultado().getString(colunaNome),
					getResultado().getString(colunaSigla)
				);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			s = null;
		}
		
		encerraConexaocomBanco();
		return s;
	}
	
	public ArrayList<Setor> getAll(){
		iniciaConexaoComBanco();
		setSqlQuery(
			"select * from " + getNomeTabela() + " order by sigla"
		);
		
		ArrayList<Setor> setores = new ArrayList<Setor>();
		Setor s;
		
		try {
//			monta o statement
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
//			executa a query
			setResultado(
				getStatement().executeQuery()
			);
		
//			traduzir o resultado
			while(getResultado().next()){
				s = new Setor(
					getResultado().getString(colunaCodigo),
					getResultado().getString(colunaNome),
					getResultado().getString(colunaSigla)
				);	
				setores.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			setores = new ArrayList<Setor>();
		}
		
		encerraConexaocomBanco();
		return setores;		
	}
}
