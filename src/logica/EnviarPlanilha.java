package logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EnviarPlanilha implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
	/*	String origem = (String) pedido.getSession().getAttribute("origem");
		int n = Integer.parseInt((String) pedido.getSession().getAttribute("n"));
		*/
		
		return "/adds/ajuda.jsp";
	}

}
