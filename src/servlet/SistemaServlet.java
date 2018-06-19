package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logica.Logica;

@WebServlet("/sistema")
public class SistemaServlet extends HttpServlet {
	private static final long serialVersionUID = -5866444462741496740L;
	
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta) throws ServletException, IOException {
		final String pacote = "logica.";
		
		String parametro = pedido.getParameter("logica");
        String nomeDaClasse = pacote + parametro;

        try {
            Class<?> classe = Class.forName(nomeDaClasse);

            Logica logica = (Logica) classe.newInstance();
            String pagina = logica.executa(pedido, resposta);

            pedido.getRequestDispatcher(pagina).forward(pedido, resposta);

        } catch (Exception e) {
            throw new ServletException(
                "A lógica de negócios causou uma exceção", e);
        }
		
	}

}
