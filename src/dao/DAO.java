/*
 * Super classe com dados e m�todos usuados por todos os DAO.
 * Todo DAO deve ser uma extens�o dessa classe. 
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
	private boolean fecharConexao = true;
			
	protected DAO (String tabelaDB, Connection conexao){
//		recebe a conexao de outra classe. � usada quando um DAO chama outro para evitar abertura de novas conex�es
		this.nomeTabela = tabelaDB;
		this.dbConnection = conexao;
		this.fecharConexao = false;
	}
	
	protected DAO (String tabelaDB){
		this.nomeTabela = tabelaDB;
	}

	protected void iniciaConexaoComBanco() {
		// inicia a conex�o com o banco de dados
		if (dbConnection == null)
//			s� inicia uma nova conex�o caso ela ainda n�o exista
			this.dbConnection = new DBConnection(ip, nomeBanco, usuarioBanco, senhaBanco).getConnection();		
	}

	protected void encerraConexaocomBanco() {
		// fecha a conex�o com o banco e limpa as vari�veis para "liberar mem�ria"
		try {
			if (fecharConexao)
				/*
				 * s� fecha a conex�o caso o DAO n�o seja dependente de outros
				 * exemplo: UsuarioDAO chama SetorDAO. SetorDAO n�o fecha a conexao, ent�o essa vari�vel de controle
				 * fica como falsa pois UsuarioDAO ainda pode fazer novos acessos ao banco
				*/
				this.dbConnection.close();
			
			this.statement.close();
			this.limpaVariaveis();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void limpaVariaveis() {
		// reseta o conte�do de todas as vari�veis desse objeto
		this.resultado = null;
		this.sqlQuery = null;
//		this.statement = null;
	}
}
