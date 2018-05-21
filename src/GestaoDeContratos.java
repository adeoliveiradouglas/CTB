import java.util.ArrayList;

import dao.UsuarioDAO;
import entity.Usuario;

public class GestaoDeContratos {

	public static void main(String[] args) {
//		Usuario usuario = new Usuario("Extraterrestre", "et", "$2a$10$C9eu2/MTCpgB6XuNXvMRi.0o45dSRKSViS3lFdks1gFidr9t2fzeK", true);
		UsuarioDAO dbUsuario = new UsuarioDAO("gestaodecontratos","douglas", "administrador", "10.95.1.247");

		
//		dbUsuario.inserir(usuario);
		
		@SuppressWarnings("unused")
		ArrayList<Usuario> usuarios = dbUsuario.getAll();
		for(int i=0;i<1;++i){
			
		}
	}
}
