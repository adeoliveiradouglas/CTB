/*
 * Classe responsável por acessar os dados de usuario no banco de dados
 */

package dao;

import java.util.ArrayList;

import entity.Usuario;

public class UsuarioDAO extends DAO {
	//Nome das colunas no banco de dados
	String  nomeColunaMatricula = "matricula", 
			nomeColunaNome = "nome", 
			nomeColunaEmail = "email", 
			nomeColunaSenha = "senha",
			nomeColunaSetor = "setor_codigo";

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
		
	}

	public void atualizar(Usuario usuario) {
		
	}

	public Usuario getByEmail(String email) {
		return null;
	}

	public ArrayList<Usuario> getAll() {
		return null;
	}

	/*private ArrayList<Usuario> traduzirResultset() {
		return null;
	}*/
}
