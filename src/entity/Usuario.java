/*
 * Dados de um usuario no banco de dados
 * 
 * Usando framework Lombok para gerar os getters e setters da classe através da anotação "@Data"
 * */

package entity;

import lombok.Data;

@Data
public class Usuario{
	private int id,
				matricula;
	private String nome,
				   email,
				   senha;
	private Setor setor;
	private Cargo cargo;
	
	public Usuario() {}
	
	public Usuario(int matricula, String nome, String email, String senha, Setor setor, Cargo cargo) {
		this.matricula = matricula;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.setor = setor;
		this.cargo = cargo;
	}

	public Usuario(int id, int matricula, String nome, String email, String senha, Setor setor, Cargo cargo) {
		this.id = id;
		this.matricula = matricula;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.setor = setor;
		this.cargo = cargo;
	}
}
