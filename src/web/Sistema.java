/*
 * Servlet padrão para redirecionamento de páginas
 * uso = sistema?logica=oquefazer
 */

package web;

import java.io.IOException;

import javax.annotation.ManagedBean;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ManagedBean
public class Sistema {
	private static final long serialVersionUID = -5866444462741496740L;

	protected void service(String acao)
			throws ServletException, IOException {
		final String pacote = "web.";
		String pagina;
				
		String nomeDaClasse = pacote + acao;
		Class<?> classe;
		Logica logica;
		
		try {
			classe = Class.forName(nomeDaClasse);

			logica = (Logica) classe.newInstance();
			pagina = logica.executa(pedido, resposta);
//			System.out.println(pagina + " solicitada");
			pedido.getRequestDispatcher(pagina).forward(pedido, resposta);

		} catch (ClassNotFoundException cnfe){
			nomeDaClasse = pacote + "TelaLogin";
			try {
				classe = Class.forName(nomeDaClasse);
				logica = (Logica) classe.newInstance();
				pagina = logica.executa(pedido, resposta);
				pedido.getRequestDispatcher(pagina).forward(pedido, resposta);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch(NullPointerException npe){
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
