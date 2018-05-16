package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entity.Usuario;

import DBConnection.DBConnection;

public class DAO {
	private Connection dbConnection;
	private String sqlQuery = null, nomeTabela = null, nomeBanco = null,
			usuarioBanco = null, senhaBanco = null;
	private ResultSet select = null;
	private PreparedStatement stmt = null;

	public DAO(String nomeDB, String usuarioDB, String senhaDB, String tabelaBD) {
		this.nomeBanco = nomeDB;
		this.usuarioBanco = usuarioDB;
		this.senhaBanco = senhaDB;
		this.nomeTabela = tabelaBD;		
	}

	public Connection getDbConnection() {
		return dbConnection;
	}

	public String getSqlQuery() {
		return sqlQuery;
	}

	public ResultSet getSelect() {
		return select;
	}

	public PreparedStatement getStatement() {
		return stmt;
	}

	public String getNomeTabela() {
		return nomeTabela;
	}

	public void setDbConnection(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	public void setSelect(ResultSet select) {
		this.select = select;
	}

	public void setStatement(PreparedStatement stmt) {
		this.stmt = stmt;
	}

	public void setNomeTabela(String n) {
		nomeTabela = n;
	}

	protected void iniciaConexaoComBanco() {
		// inicia a conex�o com o banco de dados
		dbConnection = new DBConnection(nomeBanco, usuarioBanco, senhaBanco)
		.getConnection();
	}

	protected void encerraConexaocomBanco() {
		try {
			dbConnection.close();
			limpaVariaveis();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void limpaVariaveis() {
		// reseta o conte�do de todas as vari�veis desse objeto
		this.select = null;
		this.sqlQuery = null;
		this.stmt = null;
	}

	// CRUD para usuario
	public void inserir(Usuario u) {
		// TODO Auto-generated method stub
	}

	public void atualizar(Usuario u) {

	}

	public Usuario getByName(Usuario u) {
		return null;
	}

	public ArrayList<Usuario> getAll() {
		return null;
	}

	public Usuario getByName(String nome) {
		return null;
	}

	public void delete(Usuario usuario) {

	}
	
	//CRUD para grupo
}
