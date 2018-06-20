package logica;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import entity.Usuario;
import sun.misc.BASE64Encoder;

public class Login implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		//Busca usuario no banco
		Usuario u = new UsuarioDAO().getByEmail(
						pedido.getParameter("email")
					);
		
		//criptografa senha digitada. A comparação é feita com as senhas criptografadas
		String senha = criptografa(
					   		pedido.getParameter("senha")
					   );
		
		if(u != null && senha.equals(u.getSenha())){
			HttpServletRequest req = (HttpServletRequest) pedido;
			req.getSession().setAttribute("usuario", u);
			return "/" + u.getCargo() + "/logado.html";
		} else {
			return "errosPag/403.html";
		}
}
	
	public String criptografa(String senha) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(senha.getBytes());
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(digest.digest());
		} catch (NoSuchAlgorithmException ns) {
			ns.printStackTrace();
		}
		return senha;
	}

}
