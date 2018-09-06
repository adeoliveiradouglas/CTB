/*
 * Classe responsável por armazenar os dados da tabela "cargo" do banco 
 * Getter e setter gerados através da anotação @Data do framework lombok
 */

package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class Cargo {
	public Cargo() {
		// TODO Auto-generated constructor stub
	}
	int id;
	String nome,
		   descricao;	
}
