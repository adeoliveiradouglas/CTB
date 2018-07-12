/*
 * Responsável por acessar os dados do banco
 * Não há métodos de atualização, insert ou de delete pois não há gerenciamento de dados de setor 
 */

package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Setor;

public class SetorDAO extends DAO{
	private final String colunaNome = getNomeTabela() + ".nome",
						 colunaCodigo = getNomeTabela() + ".codigo",
	   		 			 colunaSigla = getNomeTabela() + ".sigla";

	public SetorDAO(String nomeDB, String usuarioDB, String senhaDB) {
		super(nomeDB, usuarioDB, senhaDB, "setor");
	}
	
	public SetorDAO(String nomeDB, String usuarioDB, String senhaDB, String ip) {
		super(nomeDB, usuarioDB, senhaDB, "setor", ip);
	}

	public SetorDAO(){
		super("setor");
	}
	
	public Setor getByCodigo(String codigo){
		iniciaConexaoComBanco();
		
//		monta a query
		setSqlQuery(
//			select * from setor where codigo = "codigoInserido" 
			"select * from " + 
			getNomeTabela() + 
			" where "+ 
			this.colunaCodigo + 
			" = ?"	
		);
		
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
			
		} catch (SQLException e) {
			e.printStackTrace();
			encerraConexaocomBanco();
			return null;
		} 
		
//		traduz o resultado para um objeto Setor
		Setor s = null;
		try {
			while (getResultado().next()){
				s = new Setor(
					getResultado().getString(colunaCodigo),
					getResultado().getString(colunaNome),
					getResultado().getString(colunaSigla)
				);				
			}
		} catch (SQLException e) {
			s = null;
			e.printStackTrace();
		}
		
		encerraConexaocomBanco();
		return s;
	}
	
	public Setor getBySigla(String sigla){
		iniciaConexaoComBanco();
		
//		monta a query
		setSqlQuery(
//			select * from setor where sigla = "siglaInserida" 
			"select * from " + 
			getNomeTabela() + 
			" where "+ 
			this.colunaSigla + 
			" = ?"	
		);
		
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
			
		} catch (SQLException e) {
			e.printStackTrace();
			encerraConexaocomBanco();
			return null;
		} 
		
//		traduz o resultado para um objeto Setor
		Setor s = null;
		try {
			while (getResultado().next()){
				s = new Setor(
					getResultado().getString(colunaCodigo),
					getResultado().getString(colunaNome),
					getResultado().getString(colunaSigla)
				);				
			}
		} catch (SQLException e) {
			s = null;
			e.printStackTrace();
		}
		
		encerraConexaocomBanco();
		return s;
	}
	public ArrayList<Setor> getAll(){
		iniciaConexaoComBanco();
		setSqlQuery(
			"select * from " + getNomeTabela() + " order by sigla"
		);
		
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
//		traduzir o resultado
		ArrayList<Setor> setores = new ArrayList<Setor>();
		Setor s;
		try {
			while(getResultado().next()){
				s = new Setor(
					getResultado().getString(colunaCodigo),
					getResultado().getString(colunaNome),
					getResultado().getString(colunaSigla)
				);	
				setores.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		encerraConexaocomBanco();
		return setores;		
	}
}
