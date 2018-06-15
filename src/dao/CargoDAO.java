package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import entity.Cargo;
import lombok.Getter;

public class CargoDAO extends DAO{
	@Getter
	private final String colunaId = super.getNomeTabela() + ".id",
						 colunaNome = super.getNomeTabela() + ".nome",
						 colunaDescricao = super.getNomeTabela() + ".descricao";
	
	public CargoDAO(String nomeDB, String usuarioDB, String senhaDB) {
		super(nomeDB, usuarioDB, senhaDB, "cargo");
	}
	
	public CargoDAO(String nomeDB, String usuarioDB, String senhaDB, String ip) {
		super(nomeDB, usuarioDB, senhaDB, "cargo", ip);
	}
	
	public CargoDAO(){
		super("cargo");
	}
	
	public Cargo getByCodigo(int codigo){
		iniciaConexaoComBanco();
		
		/*Exemplo de query
		select * from cargo where id = codigo;*/
		super.setSqlQuery(
			"select * from cargo where " + colunaId + " = ?"
		);
		
		try {
			super.setStatement(
				super.getDbConnection().prepareStatement(
					super.getSqlQuery()
				)
			);
			
//			Preenche o statement
			super.getStatement().setInt(
				1,
				codigo
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
		
//		Traduzir para objeto Cargo
		Cargo c = null;
		try {
			super.getResultado().next();
			c = new Cargo(
				super.getResultado().getInt(colunaId),
				super.getResultado().getString(colunaNome),
				super.getResultado().getString(colunaDescricao)
				
			);
		} catch (SQLException e) {
			e.printStackTrace();
			encerraConexaocomBanco();
			return null;
		}
				
		encerraConexaocomBanco();
		return c;
	}
	
	public ArrayList<Cargo> getAll(){
		iniciaConexaoComBanco();
		
//		monta a query
//		select * from cargo 
		super.setSqlQuery(
			"select * from " + super.getNomeTabela()
		);
		
		try {
			super.setStatement(
				super.getDbConnection().prepareStatement(
					super.getSqlQuery()
				)
			);
			
			super.setResultado(
				super.getStatement().executeQuery()
			);
		} catch (SQLException e) {
			encerraConexaocomBanco();
			e.printStackTrace();
			return null;
		}
		
		ArrayList<Cargo> lc = new ArrayList<>();
		Cargo c;
//		traduzir resultSet para uma lista
		try {
			while (super.getResultado().next()){
				c = new Cargo(
					super.getResultado().getInt(colunaId),
					super.getResultado().getString(colunaNome),
					super.getResultado().getString(colunaDescricao)
				);
				lc.add(c);
			}
		} catch (SQLException e) {
			encerraConexaocomBanco();
			e.printStackTrace();
			return null;
		}
		
		encerraConexaocomBanco();
		return lc;
	}

	public Cargo getByNome(String nome) {
		iniciaConexaoComBanco();
		
		/*Exemplo de query
		select * from cargo where id = codigo;*/
		super.setSqlQuery(
			"select * from cargo where " + colunaNome + " = ?"
		);
		
		try {
			super.setStatement(
				super.getDbConnection().prepareStatement(
					super.getSqlQuery()
				)
			);
			
//			Preenche o statement
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
		
//		Traduzir para objeto Cargo
		Cargo c = null;
		try {
			super.getResultado().next();
			c = new Cargo(
				super.getResultado().getInt(colunaId),
				super.getResultado().getString(colunaNome),
				super.getResultado().getString(colunaDescricao)
			);
		} catch (SQLException e) {
			e.printStackTrace();
			encerraConexaocomBanco();
			return null;
		}
				
		encerraConexaocomBanco();
		return c;
	}	
}
