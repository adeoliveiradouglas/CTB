package logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerContrato implements Logica {

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		String adiciona = pedido.getParameter("adicionaProcesso"),
				origem = pedido.getParameter("origem"),
				n = pedido.getParameter("n");
		return "/Comum/verContrato.jsp?adicionaProcesso="+adiciona+"&origem="+origem+"&n="+n;
	}
}
