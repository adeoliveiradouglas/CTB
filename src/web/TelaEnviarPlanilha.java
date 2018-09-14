package web;

import javax.servlet.http.HttpServletRequest;

public class TelaEnviarPlanilha implements Logica{

	@Override
	public String executa(HttpServletRequest pedido) throws Exception {
		return "/Gestor/enviarPlanilha.jsp";
	}

}
