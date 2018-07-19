package logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TelaNovoProcesso implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		System.out.println(pedido.getParameter("id"));
		return "/Gestor/novoProcesso.jsp";
	}

}
