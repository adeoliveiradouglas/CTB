import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import dao.UsuarioNovoDAO;
import sun.misc.BASE64Encoder;

/*
 * Classe main não utilizada no decorrer do programa
 * Usada somente para testes durante o desenvolvimento
 */

public class GestaoDeContratos {

	public static void main(String[] args) {
		new UsuarioNovoDAO().deleteByMatricula(123123);
	}
	
	
	public static String criptografa(String senha){
//		classe usada para criptografar as senhas
		try{
		 MessageDigest digest = MessageDigest.getInstance("SHA-256");
		               digest.update(senha.getBytes());
		 BASE64Encoder encoder = new BASE64Encoder();
		        return encoder.encode(digest.digest());
		}catch(NoSuchAlgorithmException ns){
			ns.printStackTrace();
		}
		return senha;
	}
	
}
