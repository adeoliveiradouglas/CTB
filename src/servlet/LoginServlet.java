/*
 * Servlet respons�vel por autenticar o usu�rio no sistema 
 */

package servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import entity.Usuario;
import sun.misc.BASE64Encoder;

@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = -6756540979800400483L;

	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta) throws IOException, ServletException {
		//Busca usuario no banco
		Usuario u = new UsuarioDAO().getByEmail(
						pedido.getParameter("email")
					);
		
		//criptografa senha digitada. A compara��o � feita com as senhas criptografadas
		String senha = criptografa(
					   		pedido.getParameter("senha")
					   );
		
		if(u != null && senha.equals(u.getSenha())){
			pedido.getRequestDispatcher("/logado.html").forward(pedido, resposta);
		} else {
			pedido.getRequestDispatcher("/usuarioexiste").forward(pedido, resposta);
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
