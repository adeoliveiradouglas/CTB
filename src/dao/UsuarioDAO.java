package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DBConnection.DBConnection;
import entity.UsuarioEntity;

public class UsuarioDAO {

	private Connection dbConnection; 
	private String nomeTabela = "usuario";
	
	public UsuarioDAO(){
		this.dbConnection = (Connection) new DBConnection("webapp", "douglas", "administrador").getConnection();
	}
	
	public void insert(UsuarioEntity usuario){
		String sql = "insert into " +
					 nomeTabela + //nome da tabela
					 " (nome, login, senha, ativo)" + //campos para inserir na tabela 
					 " values (?,?,?,1)";
		
		try {
			PreparedStatement stmt = dbConnection.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getLogin());
			stmt.setString(3, usuario.getSenha());
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
	
	
