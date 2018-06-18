import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import dao.UsuarioDAO;
import entity.Usuario;
import sun.misc.BASE64Encoder;

/*
 * Classe main não utilizada no decorrer do programa
 * Usada somente para testes durante o desenvolvimento
 * senha criptografada  com spring security asd = $2a$10$k1SV2r4SH9DcZloBEndktOo2ePCxYeSCOBzpmWuMJg3WHIYRSMZ62 
 */

public class GestaoDeContratos {

	public static void main(String[] args) {
		
		Usuario u = new UsuarioDAO().getByEmail("adeoliveiradouglas@gmail.com");
		
		System.out.println(u.getNome());
		
		String s = criptografa("gestor");
		System.out.println(s);
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
