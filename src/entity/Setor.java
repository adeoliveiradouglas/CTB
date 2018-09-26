/*
 * Classe responsável por armazenar em memória a tabela Setor
 * Usando framework Lombok para gerar os getters e setters da classe através da anotação "@Data" e construtores com "@AllArgs..."
 */

package entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Setor {
	private String codigo, nome, sigla;
}
