/*
 * Classe abstrata responsável por armazenar dados das tabelas fontePagante, recurso e uso
 * Não foi visto necessidade de separar em três classes diferentes pois tem o mesmo tipo e quantidade de dados
 * e o relacionamento com outras classes é feito através do nome
 */

package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class Outro {
	int id;
	String nome;
}
