package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DBConnection.DBConnection;
import lombok.Data;

@Data 
public class DAO {
	private Connection dbConnection;
	private String sqlQuery = null, 
				   nomeTabela = null, 
				   nomeBanco = null,
				   usuarioBanco = null, 
				   senhaBanco = null,
				   ip = "localhost";
	private ResultSet select = null;
	private PreparedStatement statement = null;
	
	public DAO(String nomeDB, String usuarioDB, String senhaDB, String tabelaBD, String ip) {
		this.nomeBanco = nomeDB;
		this.usuarioBanco = usuarioDB;
		this.senhaBanco = senhaDB;
		this.nomeTabela = tabelaBD;
		this.ip = ip;
	}

	public DAO(String nomeDB, String usuarioDB, String senhaDB, String tabelaBD) {
		this.nomeBanco = nomeDB;
		this.usuarioBanco = usuarioDB;
		this.senhaBanco = senhaDB;
		this.nomeTabela = tabelaBD;		
	}

	

	protected void iniciaConexaoComBanco() {
		// inicia a conex�o com o banco de dados
		this.dbConnection = new DBConnection(ip, nomeBanco, usuarioBanco, senhaBanco).getConnection();
	}

	protected void encerraConexaocomBanco() {
		// fecha a conex�o com o banco e limpa as vari�veis para "liberar mem�ria"
		try {
			this.dbConnection.close();
			this.limpaVariaveis();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void limpaVariaveis() {
		// reseta o conte�do de todas as vari�veis desse objeto
		this.select = null;
		this.sqlQuery = null;
		this.statement = null;
	}
}
