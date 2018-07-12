/*
 * Classe responsável por armazenar os dados da tabela "cargo" do banco 
 * Getter e setter gerados através da anotação @Data do framework lombok
 */

package entity;

import lombok.Data;

@Data
public class Cargo {
	int id;
	String nome,
		   descricao;
		
	public Cargo(int id, String nome, String descricao) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public Cargo(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
}
