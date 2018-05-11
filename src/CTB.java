import dao.UsuarioDAO;
import entity.UsuarioEntity;

public class CTB {

	public static void main(String[] args){
		UsuarioDAO dbUsuario = new UsuarioDAO();
		UsuarioEntity usuario = new UsuarioEntity("Extraterrestre", "et", "$2a$10$C9eu2/MTCpgB6XuNXvMRi.0o45dSRKSViS3lFdks1gFidr9t2fzeK");
		dbUsuario.insert(usuario);
	}
}