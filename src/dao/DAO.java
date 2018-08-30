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
	private boolean fecharConexao = true;
			
	protected DAO (String tabelaDB, Connection conexao){
//		recebe a conexao de outra classe. É usada quando um DAO chama outro para evitar abertura de novas conexões
		this.nomeTabela = tabelaDB;
		this.dbConnection = conexao;
		this.fecharConexao = false;
	}
	
	protected DAO (String tabelaDB){
		this.nomeTabela = tabelaDB;
	}

	protected void iniciaConexaoComBanco() {
		// inicia a conexão com o banco de dados
		if (dbConnection == null)
//			só inicia uma nova conexão caso ela ainda não exista
			this.dbConnection = new DBConnection(ip, nomeBanco, usuarioBanco, senhaBanco).getConnection();		
	}

	protected void encerraConexaocomBanco() {
		// fecha a conexão com o banco e limpa as variáveis para "liberar memória"
		try {
			if (fecharConexao)
				/*
				 * só fecha a conexão caso o DAO não seja dependente de outros
				 * exemplo: UsuarioDAO chama SetorDAO. SetorDAO não fecha a conexao, então essa variável de controle
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
		// reseta o conteúdo de todas as variáveis desse objeto
		this.resultado = null;
		this.sqlQuery = null;
//		this.statement = null;
	}
}
