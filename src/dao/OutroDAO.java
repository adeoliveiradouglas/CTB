/*
 * Acesso às tabelas uso, recurso e fontepagante  
 */

package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import entity.Outro;

public class OutroDAO extends DAO{
	private final String colunaId = super.getNomeTabela() + ".id",
						 colunaNome = super.getNomeTabela() + ".nome";	
	
	OutroDAO(String nomeTabela) {
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
			super.getResultado().next();
			
//			Tipo abstrato para dados das tabelas uso, recurso e fontepagante
			Outro o = new Outro();
			o.setId(
				super.getResultado().getInt(colunaId)
			);
			
			o.setNome(
				super.getResultado().getString(colunaNome)
			);
			
			lista.add(o);
		} catch (SQLException e) {
			e.printStackTrace();
			encerraConexaocomBanco();
			return null;
		}		
		
		encerraConexaocomBanco();
		return lista;
	}
	
	public Outro getByNome(String nome){
		iniciaConexaoComBanco();
		
		/*Exemplo de query
		  select * from uso/fontepagante/recurso where nome = parametro*/
		super.setSqlQuery(
			"select * from " + super.getNomeTabela() + " where " + colunaNome + " = ?"
		);
		
		try {
//			Prepara o statement
			super.setStatement(
				super.getDbConnection().prepareStatement(
					super.getSqlQuery()
				)
			);
			
			super.getStatement().setString(
				1, 
				nome
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
		Outro o = new Outro();
		try {
			super.getResultado().next();

			o.setId(
				super.getResultado().getInt(colunaId)
			);
			
			o.setNome(
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
