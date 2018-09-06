/*
 * Dados de um usuario no banco de dados
 * 
 * Usando framework Lombok para gerar os getters e setters da classe através da anotação "@Data"
 * */

package entity;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class Usuario{
	private int id,
				matricula;
	private String nome,
				   email,
				   senha;
	private Setor setor;
	private ArrayList<Cargo> cargo;
}
