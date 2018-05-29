/*
 * Classe respons�vel por guardar as permiss�es do sistema
 * 
 * Usando framework Lombok para gerar os getters e setters da classe atrav�s da anota��o "@Data"
 */

package entity;

import lombok.Data;

@Data
public class Permissao {
	private int id;
	private String nome;
	private String descricao;
		
	public Permissao(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public Permissao(int id, String nome, String descricao) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}	
}
