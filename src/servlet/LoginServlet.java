/*
 * Servlet respons�vel por autenticar o usu�rio no sistema 
 */

package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import dao.UsuarioDAO;
import entity.Usuario;

@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta) throws IOException {
		final String usuarioB = "servidorApp",
					 banco = "gestaodecontratos",
					 senhaB = "suporte2017";
		
		PrintWriter out = resposta.getWriter();
		PasswordEncoder pe = new BCryptPasswordEncoder(); // objeto respons�vel
															// por criptografar
															// uma string
		UsuarioDAO dbUsuario = new UsuarioDAO(banco, usuarioB, senhaB);
		Usuario u;
		String email = pedido.getParameter("email");
		// Busca usuario no banco usando o email
		try {
			u = dbUsuario.getByEmail(email);
		} catch (Exception e) {
			u = null;
		}
		
		// Criptografa senha inserida
		String senha = null;
		try {
			senha = pe.encode(pedido.getParameter("senha"));
		} catch (IllegalStateException e) {
			//N�o conseguiu carregar classe BCryptPasswordEncoder que criptografa string
			System.out.println("IllegalStateException");
		} catch (Exception e){
			e.printStackTrace();
		}

		out.println("<html>");
		out.println("<body>");

		// Compara senhas criptografadas
		if (u != null) {
			if (pedido.getParameter(senha).equals(u.getSenha())) {
				// Caso a senha esteja correta
				out.println("<p>Logado</p>");
			} else {
				// Caso senha errada
				out.println("<p>Senha incorreta ou usuario n�o existe</p>");
			}
		} else {
			// Caso usuario n�o exista
			out.println("<p>Senha incorreta ou usuario n�o existe</p>");
		}
		out.println("</body>");
		out.println("</html>");
	}
}
