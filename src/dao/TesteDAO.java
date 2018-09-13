package dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

public class TesteDAO extends DAO{

	public TesteDAO(String tabelaDB) {
		super(tabelaDB);
	}
	
	public void inserir(BigDecimal valor){
		iniciaConexaoComBanco();
		
		setSqlQuery(
			"insert into " + getNomeTabela() + " (numero) values (?)"
		);

		try{
			setStatement(getDbConnection().prepareStatement(getSqlQuery()));
			getStatement().setBigDecimal(1, valor);
			getStatement().executeUpdate();
		}catch(SQLException e){
			System.out.println(e);;
		} finally {
			encerraConexaocomBanco();
		}
	}

	public ArrayList<BigDecimal> getAll() {
		iniciaConexaoComBanco();
		
		setSqlQuery(
			"select * from " + getNomeTabela()
		);

		ArrayList<BigDecimal> valores = new ArrayList<BigDecimal>();
		try{
			setStatement(getDbConnection().prepareStatement(getSqlQuery()));
			setResultado(getStatement().executeQuery());
			
			while(getResultado().next()){
				valores.add(getResultado().getBigDecimal("numero"));
			}
		}catch(SQLException e){
			System.out.println(e);;
			valores = new ArrayList<BigDecimal>();
		} finally {
			encerraConexaocomBanco();
		}
		
		return valores;
	}
}
