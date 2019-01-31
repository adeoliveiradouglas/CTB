package web;

import java.util.EmptyStackException;
import java.util.Stack;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VoltarPagina implements Logica{

	@Override
	@SuppressWarnings("unchecked")
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		Stack<String> paginas = (Stack<String>) pedido.getSession().getAttribute("pilhaPaginas");
		String pagina, pag2, backup = "/gestaodecontratos/sistema?logica=TelaLogin";
		try {
			paginas.pop();
			paginas.pop();
			do {} while(!paginas.pop().contains("jsp"));
			pagina = paginas.pop();
		} catch (EmptyStackException e) {
			pagina = backup;
		}
		int p = pagina.lastIndexOf("/");
		pag2 = pagina.substring(
					p + 1, 
					pagina.length()
				);
		System.out.println("função: " + pag2);
		return pag2;
	}

}
