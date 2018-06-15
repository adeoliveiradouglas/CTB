/*
 * Servlet responsável por autenticar o usuário no sistema 
 */

package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = -6756540979800400483L;

	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta) throws IOException {
		try {
			pedido.getRequestDispatcher("esqueceuasenha").forward(pedido, resposta);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
//		PrintWriter out = resposta.getWriter();
//		PasswordEncoder pe; // objeto responsável
															// por criptografar
															// uma string
		Usuario u;
		String email = pedido.getParameter("email");
		// Busca usuario no banco usando o email
		try {
			u = new UsuarioDAO().getByEmail(email);
		} catch (Exception e) {
			u = null;
		}
		
		// Criptografa senha inserida
		String senha = null;
		try {
			senha = new BCryptPasswordEncoder().encode(pedido.getParameter("senha"));
		} catch (IllegalStateException e) {
			//Não conseguiu carregar classe BCryptPasswordEncoder que criptografa string
			System.out.println("IllegalStateException");
		} catch (Exception e){
			e.printStackTrace();
		}
		
		// Compara senhas criptografadas
		if (u != null) {
			if (pedido.getParameter(senha).equals(u.getSenha())) {
				// Caso a senha esteja correta
				try {
					pedido.getRequestDispatcher("/logado.html").forward(pedido, resposta);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				// Caso senha errada
				try {
					pedido.getRequestDispatcher("/erro.html").forward(pedido, resposta);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			// Caso usuario não exista
			try {
				pedido.getRequestDispatcher("/erro.html").forward(pedido, resposta);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	}
}
