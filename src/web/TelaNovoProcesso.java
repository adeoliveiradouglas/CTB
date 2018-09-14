package web;

import javax.servlet.http.HttpServletRequest;

public class TelaNovoProcesso implements Logica{

	@Override
	public String executa(HttpServletRequest pedido) throws Exception {
		return "/Gestor/novoProcesso.jsp";
	}

}
