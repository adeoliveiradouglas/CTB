/*
 * Acesso às tabelas uso, recurso e fontepagante  
 */

package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import entity.Outro;

public class OutroDAO extends DAO{
	private String colunaId = getNomeTabela() + ".id",
						 colunaNome = getNomeTabela() + ".nome";	
	
	public OutroDAO(String nomeTabela) {
		super(nomeTabela);
	}

	public ArrayList<Outro> getAll(){
		iniciaConexaoComBanco();
		ArrayList<Outro> lista = new ArrayList<Outro>();
		
		/*Exemplo de query
		  select * from uso/fontepagante/recurso*/
		setSqlQuery(
			"select * from " + getNomeTabela()
		);
		
		try {
//			Prepara o statement
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
//			Executa a query
			setResultado(
				getStatement().executeQuery()
			);
		} catch (SQLException e) {			
			e.printStackTrace();
			encerraConexaocomBanco();
			return null;
		}
		
		
		try {
			while (getResultado().next()){				
	//			Tipo abstrato para dados das tabelas uso, recurso e fontepagante
				Outro o = new Outro(
					getResultado().getInt(colunaId),
					getResultado().getString(colunaNome)
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
		setSqlQuery(
			"select * from " + getNomeTabela() + " where " + colunaId + " = ?"
		);
		
		try {
//			Prepara o statement
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
			getStatement().setInt(
				1, 
				id
			);
			
//			Executa a query
			setResultado(
				getStatement().executeQuery()
			);
		} catch (SQLException e) {			
			e.printStackTrace();
			encerraConexaocomBanco();
			return null;
		}
		
		
//		Tipo abstrato para dados das tabelas uso, recurso e fontepagante
		Outro o = null;
		try {
			getResultado().next();
			
			o = new Outro(
				getResultado().getInt(colunaId),
				getResultado().getString(colunaNome)
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
