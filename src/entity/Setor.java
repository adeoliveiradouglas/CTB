/*
 * Classe responsável por armazenar em memória a tabela Setor
 * Usando framework Lombok para gerar os getters e setters da classe através da anotação "@Data"
 */

package entity;

import lombok.Data;

@Data
public class Setor {
	private String codigo,
				   nome,
				   sigla;

	public Setor(String id, String nome, String sigla) {
		this.codigo = id;
		this.nome = nome;
		this.sigla = sigla;
	}
}
