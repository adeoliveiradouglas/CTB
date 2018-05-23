/*
 * Dados de um usuario no banco de dados
 * 
 * Usando framework Lombok para gerar os getters e setters da classe através da anotação "@Data"
 * */
package entity;

import lombok.Data;

@Data
public class Usuario {
	private int id;
	private int matricula;
	private String nome;
	private String email;
	private String senha;
	private boolean isAtivo;

	public Usuario(String nome, String email, String senha, boolean isAtivo) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.isAtivo = isAtivo;
	}

	public Usuario() {
	}
}
