/*
 * Classe responsável por armazenar os dados da tabela "cargo" do banco 
 * Getter e setter gerados através da anotação @Data do framework lombok
 */

package entity;

import lombok.Data;

@Data
public class Cargo {
	int id;
	String nome;
		
	public Cargo(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	
}
