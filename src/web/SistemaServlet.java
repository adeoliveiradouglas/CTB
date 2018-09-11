/*
 * Servlet padrão para redirecionamento de páginas
 * uso = sistema?logica=oquefazer
 */

package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sistema")
public class SistemaServlet extends HttpServlet {
	private static final long serialVersionUID = -5866444462741496740L;

	protected void service(HttpServletRequest pedido, HttpServletResponse resposta)
			throws ServletException, IOException {
		final String pacote = "web.";
		String acao = pedido.getParameter("logica"),
			   pagina;
				
		String nomeDaClasse = pacote + acao;
		Class<?> classe;
		Logica logica;
		
		try {
			classe = Class.forName(nomeDaClasse);

			logica = (Logica) classe.newInstance();
			pagina = logica.executa(pedido, resposta);
//			System.out.println(pagina + " solicitada");
			pedido.getRequestDispatcher(pagina).forward(pedido, resposta);

		} catch (ClassNotFoundException | NullPointerException cnfe){
			nomeDaClasse = pacote + "TelaLogin";
			try {
				classe = Class.forName(nomeDaClasse);
				logica = (Logica) classe.newInstance();
				pagina = logica.executa(pedido, resposta);
				pedido.getRequestDispatcher(pagina).forward(pedido, resposta);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
