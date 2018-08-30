/*
 * Acesso às tabelas uso, recurso e fontepagante  
 */

package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import entity.Outro;

public class OutroDAO extends DAO{
	private String colunaId = getNomeTabela() + ".id",
						 colunaNome = getNomeTabela() + ".nome";	
	
	public OutroDAO(String nomeTabela) {
		super(nomeTabela);
	}
	
	public OutroDAO(String nomeTabela, Connection conexao) {
		super(nomeTabela, conexao);
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
			lista = new ArrayList<Outro>();
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
		
//		Tipo abstrato para dados das tabelas uso, recurso e fontepagante
		Outro o = null;
		
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
		
			if(getResultado().next()){
				o = new Outro(
					getResultado().getInt(colunaId),
					getResultado().getString(colunaNome)
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			o = null;
		}		
		
		encerraConexaocomBanco();
		return o;
	}
}
