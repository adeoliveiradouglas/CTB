package web;

import javax.servlet.http.HttpServletRequest;

public class Logout implements Logica{

	@Override
	public String executa(HttpServletRequest pedido) throws Exception {
		pedido.getSession().invalidate();
		
		return "sistema?logica=TelaLogin";
	}

}
