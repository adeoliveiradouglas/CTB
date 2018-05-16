/*
 * Dados de um usuário no banco de dados
 * */

package entity;

public class Usuario {
	private int id;
	private String nome,
				   login,
				   senha;
	private boolean isAtivo;	

	public Usuario(String nome, String login, String senha, boolean isAtivo) {
		super();
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.isAtivo = isAtivo;
	}

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	
	//GETTERS
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public boolean isAtivo(){
		return isAtivo;
	}
	
	//SETTERS
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}	
	
	public void setAtivo(boolean ativo){
		this.isAtivo = ativo;
	}
}
