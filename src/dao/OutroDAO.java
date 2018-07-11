/*
 * Acesso às tabelas uso, recurso e fontepagante  
 */

package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import entity.Outro;

public class OutroDAO extends DAO{
	private String colunaId = super.getNomeTabela() + ".id",
						 colunaNome = super.getNomeTabela() + ".nome";	
	
	public OutroDAO(String nomeTabela) {
		super(nomeTabela);
	}

	public ArrayList<Outro> getAll(){
		iniciaConexaoComBanco();
		ArrayList<Outro> lista = new ArrayList<Outro>();
		
		/*Exemplo de query
		  select * from uso/fontepagante/recurso*/
		super.setSqlQuery(
			"select * from " + super.getNomeTabela()
		);
		
		try {
//			Prepara o statement
			super.setStatement(
				super.getDbConnection().prepareStatement(
					super.getSqlQuery()
				)
			);
			
//			Executa a query
			super.setResultado(
				super.getStatement().executeQuery()
			);
		} catch (SQLException e) {			
			e.printStackTrace();
			encerraConexaocomBanco();
			return null;
		}
		
		
		try {
			while (super.getResultado().next()){				
	//			Tipo abstrato para dados das tabelas uso, recurso e fontepagante
				Outro o = new Outro(
					super.getResultado().getInt(colunaId),
					super.getResultado().getString(colunaNome)
				);
				lista.add(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			encerraConexaocomBanco();
			return null;
		}		
		
		encerraConexaocomBanco();
		return lista;
	}
	
	public Outro getById(int id){
		iniciaConexaoComBanco();
		
		/*Exemplo de query
		  select * from uso/fontepagante/recurso where id = parametro*/
		super.setSqlQuery(
			"select * from " + super.getNomeTabela() + " where " + colunaId + " = ?"
		);
		
		try {
//			Prepara o statement
			super.setStatement(
				super.getDbConnection().prepareStatement(
					super.getSqlQuery()
				)
			);
			
			super.getStatement().setInt(
				1, 
				id
			);
			
//			Executa a query
			super.setResultado(
				super.getStatement().executeQuery()
			);
		} catch (SQLException e) {			
			e.printStackTrace();
			encerraConexaocomBanco();
			return null;
		}
		
		
//		Tipo abstrato para dados das tabelas uso, recurso e fontepagante
		Outro o = null;
		try {
			super.getResultado().next();
			
			o = new Outro(
				super.getResultado().getInt(colunaId),
				super.getResultado().getString(colunaNome)
			);
		} catch (SQLException e) {
			e.printStackTrace();
			encerraConexaocomBanco();
			return null;
		}		
		
		encerraConexaocomBanco();
		return o;
	}
}
