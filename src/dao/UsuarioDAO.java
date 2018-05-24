/*
 * Classe responsável por acessar os dados de usuario no banco de dados
 */

package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import entity.Usuario;

public class UsuarioDAO extends DAO {
	String nomeColunaId = "idUsuario", nomeColunaNome = "nome", nomeColunaEmail = "email", nomeColunaSenha = "senha",
			nomeColunaAtivo;

	/*
	 * public UsuarioDAO(String nomeDB, String usuarioDB, String senhaDB){
	 * super(nomeDB, usuarioDB, senhaDB, "usuario"); }
	 */

	public UsuarioDAO(String nomeDB, String usuarioDB, String senhaDB, String ip) {
		super(nomeDB, usuarioDB, senhaDB, "usuario", ip);
	}

	public UsuarioDAO(String nomeDB, String usuarioDB, String senhaDB) {
		super(nomeDB, usuarioDB, senhaDB, "usuario");
	}

	public void inserir(Usuario usuario) {
		// Insere usuario no banco
		super.iniciaConexaoComBanco();
		super.setSqlQuery("insert into " + super.getNomeTabela() + // nome da
																	// tabela
				" (" + nomeColunaNome + "," + nomeColunaEmail + "," + nomeColunaSenha + "," + nomeColunaAtivo + ")" + // campos
																														// para
																														// inserir
																														// na
																														// tabela
				"values (?,?,?,?)");

		try {
			super.setStatement(super.getDbConnection().prepareStatement(super.getSqlQuery()));
			super.getStatement().setString(1, usuario.getNome());
			super.getStatement().setString(2, usuario.getEmail());
			super.getStatement().setString(3, usuario.getSenha());
			super.getStatement().setBoolean(4, true);

			super.getStatement().execute();
			super.getStatement().close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.encerraConexaocomBanco();
		}
	}

	public void atualizar(Usuario usuario) {
		super.iniciaConexaoComBanco();

		super.setSqlQuery("update " + super.getNomeTabela() + " set " + nomeColunaNome + " = ?, " + nomeColunaEmail
				+ " = ?, " + nomeColunaSenha + " = ?, " + nomeColunaAtivo + " = ?, " + // campos
																						// para
																						// inserir
																						// na
																						// tabela
				"where " + nomeColunaId + " = ?");

		try {
			super.setStatement(super.getDbConnection().prepareStatement(super.getSqlQuery()));
			super.getStatement().setString(1, usuario.getNome());
			super.getStatement().setString(2, usuario.getEmail());
			super.getStatement().setString(3, usuario.getSenha());
			super.getStatement().setBoolean(4, usuario.isAtivo());
			super.getStatement().setInt(5, usuario.getId());

			super.getStatement().execute();
			super.getStatement().close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.encerraConexaocomBanco();
		}
	}

	public void delete(Usuario usuario) { // falta implementar
		super.iniciaConexaoComBanco();
		super.encerraConexaocomBanco();
	}

	public Usuario getByName(String nome) {
		// select que retorna um unico usuario pesquisado pelo nome
		super.iniciaConexaoComBanco();
		super.setSqlQuery("select * from " + super.getNomeTabela() + // nome da
																		// tabela
				" where " + nomeColunaNome + " = ?"); // campos para pesquisar
														// na tabela
		Usuario usuario = null;

		try {
			// Buscar usuario no banco
			// prepara o Statement
			super.setStatement(super.getDbConnection().prepareStatement(super.getSqlQuery()));
			// completa as ? do Statement
			super.getStatement().setString(1, nome);
			// executa a query e guarda na variavel super.select
			super.setSelect(super.getStatement().executeQuery());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			// Traduz o resultado para um objeto Usuario
			usuario = new Usuario();
			usuario.setId(super.getSelect().getInt(nomeColunaId));
			usuario.setNome(super.getSelect().getString(nomeColunaNome));
			usuario.setEmail(super.getSelect().getString(nomeColunaEmail));
			usuario.setSenha(super.getSelect().getString(nomeColunaSenha));
			usuario.setAtivo(super.getSelect().getBoolean(nomeColunaAtivo));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.encerraConexaocomBanco();
			try {
				super.getStatement().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return usuario;
	}

	public Usuario getByEmail(String email) {
		// select que retorna um unico usuario pesquisado pelo email
		super.iniciaConexaoComBanco();
		super.setSqlQuery("select * from " + super.getNomeTabela() + // nome da
																		// tabela
				" where " + nomeColunaEmail +" = ?"); // campos para pesquisar na tabela
		Usuario usuario = null;

		try {
			// Buscar usuario no banco
			// prepara o Statement
			super.setStatement(super.getDbConnection().prepareStatement(super.getSqlQuery()));
			// completa as ? do Statement
			super.getStatement().setString(1, email);
			// executa a query e guarda na variavel super.select
			super.setSelect(super.getStatement().executeQuery());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Traduz o resultado para um objeto Usuario
		usuario = traduzirResultset();
		/* forma antes da função traduzir result
		 * usuario.setId(super.getSelect().getInt(nomeColunaId));
		 * usuario.setNome(super.getSelect().getString(nomeColunaNome));
		 * usuario.setEmail(super.getSelect().getString(nomeColunaEmail));
		 * usuario.setSenha(super.getSelect().getString(nomeColunaSenha));
		 * usuario.setAtivo(super.getSelect().getBoolean(nomeColunaAtivo));
		 */
		try {
			super.getStatement().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		super.encerraConexaocomBanco();
		
		return usuario;
	}

	public ArrayList<Usuario> getAll() {
		// select que retorna todos os usuarios cadastrados no banco
		super.iniciaConexaoComBanco();

		setSqlQuery("select * from " + getNomeTabela());
		ArrayList<Usuario> usuarios = new ArrayList<>(); // guarda os usuarios
															// da query

		try { // Buscar usuarios no banco
			super.setStatement(super.getDbConnection().prepareStatement(super.getSqlQuery()));
			super.setSelect(super.getStatement().executeQuery());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		try {
			// Traduz o resultado para um objeto Usuario e insere na lista
			while (getSelect().next()) {
				Usuario usuario = new Usuario();
				usuario.setId(getSelect().getInt("idUsuario"));
				usuario.setNome(getSelect().getString("nome"));
				usuario.setEmail(getSelect().getString("email"));
				usuario.setSenha(getSelect().getString("senha"));
				usuario.setAtivo(getSelect().getBoolean("ativo"));
				// insere na lista
				usuarios.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				super.getStatement().close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			encerraConexaocomBanco();
		}

		return usuarios;
	}

	private Usuario traduzirResultset() {
		// Traduz o resultado de uma consulta para um objeto Usuario
		Usuario usuario = new Usuario();
		boolean erroResult = false; //controla se houve erro na busca do usuario
		try {
			usuario.setId(super.getSelect().getInt(nomeColunaId));
			usuario.setNome(super.getSelect().getString(nomeColunaNome));
			usuario.setEmail(super.getSelect().getString(nomeColunaEmail));
			usuario.setSenha(super.getSelect().getString(nomeColunaSenha));
			usuario.setAtivo(super.getSelect().getBoolean(nomeColunaAtivo));
		} catch (SQLException e) {
			erroResult = true;
		}

		if (erroResult){ 
			//se houve erros no resultado retorna nada
			return null;
		} else {
			return usuario;
		}
	}
}
