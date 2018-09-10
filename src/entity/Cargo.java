/*
 * Classe respons�vel por armazenar os dados da tabela "cargo" do banco 
 * Getter e setter gerados atrav�s da anota��o @Data do framework lombok
 */

package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor 
public class Cargo {
	int id;
	String nome,
		   descricao;	
}
