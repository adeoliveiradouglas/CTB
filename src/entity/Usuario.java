/*
 * Dados de um usuario no banco de dados
 * 
 * Usando framework Lombok para gerar os getters e setters da classe atrav�s da anota��o "@Data"
 * Usu�rio SEMPRE deve ter dois cargos, um principal e o outro pode ou n�o ser v�lido, mas deve estar na lista de cargos
 * 		isso � garantido pelo DAO 
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
