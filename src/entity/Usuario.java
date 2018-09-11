/*
 * Dados de um usuario no banco de dados
 * 
 * Usando framework Lombok para gerar os getters e setters da classe através da anotação "@Data"
 * Usuário SEMPRE deve ter dois cargos, um principal e o outro pode ou não ser válido, mas deve estar na lista de cargos
 * 		isso é garantido pelo DAO 
 */

package entity;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter 
@AllArgsConstructor
@NoArgsConstructor
public class Usuario{
	@Setter
	private int id;
	private int matricula;
	private String nome,
				   email,
				   senha;
	private Setor setor;
	private ArrayList<Cargo> cargo;
}
