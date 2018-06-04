/*
 * Responsável por acessar os dados do banco
 * Não há métodos de atualização, insert ou de delete pois não há gerenciamento de dados de setor 
 */

package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Setor;

public class SetorDAO extends DAO{
	private final String colunaNome = super.getNomeTabela() + ".nome",
						 colunaCodigo = super.getNomeTabela() + ".codigo",
	   		 			 colunaSigla = super.getNomeTabela() + ".sigla";

	public SetorDAO(String nomeDB, String usuarioDB, String senhaDB) {
		super(nomeDB, usuarioDB, senhaDB, "setor");
	}
	
	public SetorDAO(String nomeDB, String usuarioDB, String senhaDB, String ip) {
		super(nomeDB, usuarioDB, senhaDB, "setor", ip);
	}

	public Setor getByCodigo(String codigo){
		iniciaConexaoComBanco();
		
//		monta a query
		super.setSqlQuery(
//			select * from setor where codigo = "codigoInserido" 
			"select * from " + 
			super.getNomeTabela() + 
			" where "+ 
			this.colunaCodigo + 
			" = ?"	
		);
		
		try {
//			monta o statement
			super.setStatement( 
//				pega da conexao
				super.getDbConnection().prepareStatement(
					super.getSqlQuery()	
				)
			);
			
//			preenche o statement
			super.getStatement().setString(
				1, 
				codigo
			);
//			executa a query
			super.setResultado(
				super.getStatement().executeQuery()
			);
			
		} catch (SQLException e) {
			e.printStackTrace();
			encerraConexaocomBanco();
			return null;
		} 
		
//		traduz o resultado para um objeto Setor
		Setor s = null;
		try {
			while (super.getResultado().next()){
				s = new Setor(
					super.getResultado().getString(colunaCodigo),
					super.getResultado().getString(colunaNome),
					super.getResultado().getString(colunaSigla)
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
		super.setSqlQuery(
//			select * from setor where sigla = "siglaInserida" 
			"select * from " + 
			super.getNomeTabela() + 
			" where "+ 
			this.colunaSigla + 
			" = ?"	
		);
		
		try {
//			monta o statement
			super.setStatement( 
//				pega da conexao
				super.getDbConnection().prepareStatement(
					super.getSqlQuery()	
				)
			);
			
//			preenche o statement
			super.getStatement().setString(
				1, 
				sigla
			);
//			executa a query
			super.setResultado(
				super.getStatement().executeQuery()
			);
			
		} catch (SQLException e) {
			e.printStackTrace();
			encerraConexaocomBanco();
			return null;
		} 
		
//		traduz o resultado para um objeto Setor
		Setor s = null;
		try {
			while (super.getResultado().next()){
				s = new Setor(
					super.getResultado().getString(colunaCodigo),
					super.getResultado().getString(colunaNome),
					super.getResultado().getString(colunaSigla)
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
		super.setSqlQuery(
			"select * from " + super.getNomeTabela()
		);
		
		try {
//			monta o statement
			super.setStatement(
				super.getDbConnection().prepareStatement(
					super.getSqlQuery()
				)
			);
			
//			executa a query
			super.setResultado(
				super.getStatement().executeQuery()
			);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
//		traduzir o resultado
		ArrayList<Setor> setores = new ArrayList<Setor>();
		Setor s;
		try {
			while(super.getResultado().next()){
				s = new Setor(
					super.getResultado().getString(colunaCodigo),
					super.getResultado().getString(colunaNome),
					super.getResultado().getString(colunaSigla)
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
