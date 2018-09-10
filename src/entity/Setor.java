/*
 * Classe respons�vel por armazenar em mem�ria a tabela Setor
 * Usando framework Lombok para gerar os getters e setters da classe atrav�s da anota��o "@Data" e construtores com "@AllArgs..."
 */

package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class Setor {
	private String codigo, nome, sigla;
}
