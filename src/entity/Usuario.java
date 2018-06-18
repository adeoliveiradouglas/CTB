/*
 * Dados de um usuario no banco de dados
 * 
 * Usando framework Lombok para gerar os getters e setters da classe através da anotação "@Data"
 * obs1: guarda apenas a sigla do setor
 * obs2: guarda apenas o nome do cargo. necessário pois existem padrões
 * */

package entity;

import lombok.Data;

@Data
public class Usuario {
	private int matricula;
	private String nome,
				   email,
				   senha,
				   setor, // vide cabecalho obs1
				   cargo; // vide cabecalho obs2
	

	public Usuario() {}


	public Usuario(int matricula, String nome, String email, String senha, String setor, String cargo) {
		super();
		this.matricula = matricula;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.setor = setor;
		this.cargo = cargo;
	} 	
}
