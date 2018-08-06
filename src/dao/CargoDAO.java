package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import entity.Cargo;
import lombok.Getter;
public class CargoDAO extends DAO{
	@Getter
	private final String colunaId = getNomeTabela() + ".id",
						 colunaNome = getNomeTabela() + ".nome",
						 colunaDescricao = getNomeTabela() + ".descricao";
	
	public CargoDAO(String nomeDB, String usuarioDB, String senhaDB) {
		super(nomeDB, usuarioDB, senhaDB, "cargo");
	}
	
	public CargoDAO(String nomeDB, String usuarioDB, String senhaDB, String ip) {
		super(nomeDB, usuarioDB, senhaDB, "cargo", ip);
	}
	
	public CargoDAO(){
		super("cargo");
	}
	
	public Cargo getById(int id){
		Cargo c = null;
		
		iniciaConexaoComBanco();
		
		/*Exemplo de query
		select * from cargo where id = codigo;*/
		setSqlQuery(
			"select * from cargo where " + colunaId + " = ?"
		);
		
		try {
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
//			Preenche o statement
			getStatement().setInt(
				1,
				id
			);
			
//			Executa a query
			setResultado(
				getStatement().executeQuery()
			);
			
//			Traduzir para objeto Cargo
			if(getResultado().next()){
				c = new Cargo(
					getResultado().getInt(colunaId),
					getResultado().getString(colunaNome),
					getResultado().getString(colunaDescricao)
					
				);
			}
		} catch (SQLException e) {
			System.out.println(e);;
			encerraConexaocomBanco();
			return null;
		}

		encerraConexaocomBanco();
		return c;
	}
	
	public ArrayList<Cargo> getAll(){
		ArrayList<Cargo> lc = new ArrayList<>();
		Cargo c;
		
		iniciaConexaoComBanco();
		
//		monta a query
//		select * from cargo 
		setSqlQuery(
			"select * from " + getNomeTabela()
		);
		
		try {
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
			setResultado(
				getStatement().executeQuery()
			);
			
//			traduzir resultSet para uma lista
			while (getResultado().next()){
				c = new Cargo(
					getResultado().getInt(colunaId),
					getResultado().getString(colunaNome),
					getResultado().getString(colunaDescricao)
				);
				lc.add(c);
			}
		} catch (SQLException e) {
			encerraConexaocomBanco();
			System.out.println(e);;
			return null;
		} 
			
		encerraConexaocomBanco();
		return lc;
	}

	public Cargo getByNome(String nome) {
		Cargo c = null;
		
		iniciaConexaoComBanco();
		
		/*Exemplo de query
		select * from cargo where id = codigo;*/
		setSqlQuery(
			"select * from cargo where " + colunaNome + " = ?"
		);
		
		try {
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
//			Preenche o statement
			getStatement().setString(
				1,
				nome
			);
			
//			Executa a query
			setResultado(
				getStatement().executeQuery()
			);
			
//			Traduzir para objeto Cargo
			if(getResultado().next()){
				c = new Cargo(
					getResultado().getInt(colunaId),
					getResultado().getString(colunaNome),
					getResultado().getString(colunaDescricao)
				);
			}
		} catch (SQLException e) {
			System.out.println(e);;
			encerraConexaocomBanco();
			return null;
		} 
		
		encerraConexaocomBanco();
		return c;
	}	
}
