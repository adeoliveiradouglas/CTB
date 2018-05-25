/*
 * Classe main não utilizada no decorrer do programa
 * Usada somente para testes durante o desenvolvimento
 * senha criptografada  com spring security asd = $2a$10$k1SV2r4SH9DcZloBEndktOo2ePCxYeSCOBzpmWuMJg3WHIYRSMZ62 
 */

import java.util.ArrayList;

import dao.UsuarioDAO;
import entity.Usuario;

public class GestaoDeContratos {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Usuario usuario = new Usuario("Extraterrestre", "et", "$2a$10$k1SV2r4SH9DcZloBEndktOo2ePCxYeSCOBzpmWuMJg3WHIYRSMZ62", true);
		UsuarioDAO dbUsuario = new UsuarioDAO("gestaodecontratos","douglas", "administrador", "10.95.1.247");
//		Usuario u2 = dbUsuario.getByName("Extraterrestre");
//		Usuario u = dbUsuario.getByEmail("et");
		
		dbUsuario.inserir(usuario);
//		dbUsuario.inserir(usuario2);
		
		ArrayList<Usuario> usuarios = dbUsuario.getAll();
		
		
		for(int i=0;i<usuarios.size();++i){
			System.out.println(usuarios.get(i).toString());
		}
	}
}
