import dao.DAO;
import dao.UsuarioDAO;
import entity.Usuario;

public class GestaoDeContratos {

	public static void main(String[] args) {
		DAO dbUsuario = new UsuarioDAO("gestaodecontratos","root", "");
		Usuario usuario = new Usuario("Extraterrestre", "et",
				"$2a$10$C9eu2/MTCpgB6XuNXvMRi.0o45dSRKSViS3lFdks1gFidr9t2fzeK", true);
		dbUsuario.inserir(usuario);
	}
}
