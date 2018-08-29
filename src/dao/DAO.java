/*
 * Super classe com dados e métodos usuados por todos os DAO.
 * Todo DAO deve ser uma extensão dessa classe. 
 */

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DBConnection.DBConnection;
import lombok.Data;

@Data 
public class DAO {
	private Connection dbConnection = null;
	private String sqlQuery = null, 
				   nomeTabela = null, 
				   nomeBanco = "gestaodecontratos",
				   usuarioBanco = "gestaodecontratos", 
				   senhaBanco = "suporte2017",
				   ip = "localhost";
	private ResultSet resultado = null;
	private PreparedStatement statement = null;
	
	/*protected DAO(String nomeDB, String usuarioDB, String senhaDB, String tabelaBD, String ip) {
		this.nomeBanco = nomeDB;
		this.usuarioBanco = usuarioDB;
		this.senhaBanco = senhaDB;
		this.nomeTabela = tabelaBD;
		this.ip = ip;
	}

	protected DAO(String nomeDB, String usuarioDB, String senhaDB, String tabelaBD) {
		this.nomeBanco = nomeDB;
		this.usuarioBanco = usuarioDB;
		this.senhaBanco = senhaDB;
		this.nomeTabela = tabelaBD;		
	}*/	
		
	protected DAO (String tabelaDB, Connection conexao){
		this.nomeTabela = tabelaDB;
		this.dbConnection = conexao;
	}
	
	protected DAO (String tabelaDB){
		this.nomeTabela = tabelaDB;
	}

	protected void iniciaConexaoComBanco() {
		// inicia a conexão com o banco de dados
		if (dbConnection == null)
			this.dbConnection = new DBConnection(ip, nomeBanco, usuarioBanco, senhaBanco).getConnection();		
	}

	protected void encerraConexaocomBanco() {
		// fecha a conexão com o banco e limpa as variáveis para "liberar memória"
		try {
			this.dbConnection.close();
			this.statement.close();
			this.limpaVariaveis();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void limpaVariaveis() {
		// reseta o conteúdo de todas as variáveis desse objeto
		this.resultado = null;
		this.sqlQuery = null;
//		this.statement = null;
	}
}
