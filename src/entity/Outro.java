/*
 * Classe abstrata respons�vel por armazenar dados das tabelas fontePagante, recurso e uso
 * N�o foi visto necessidade de separar em tr�s classes diferentes pois tem o mesmo tipo e quantidade de dados
 * e o relacionamento com outras classes � feito atrav�s do nome
 */

package entity;

import lombok.Data;

@Data
public class Outro {
	int id;
	String nome;
	
	public Outro(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
}
