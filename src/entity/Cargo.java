/*
 * Classe responsável por armazenar os dados da tabela "cargo" do banco 
 * Getter e setter gerados através da anotação @Data do framework lombok
 */

package entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class Cargo {
	int id;
	String nome,
		   descricao;	
}
