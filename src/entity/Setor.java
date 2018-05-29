/*
 * Classe respons�vel por armazenar em mem�ria a tabela Setor
 * Usando framework Lombok para gerar os getters e setters da classe atrav�s da anota��o "@Data"
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
