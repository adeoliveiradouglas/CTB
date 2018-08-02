package logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Contrato;
import entity.Processo;

public class VerProcesso implements Logica {

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		String origem = pedido.getParameter("origem");
		int i = Integer.parseInt(pedido.getParameter("i"));
				
		Contrato c = (Contrato) pedido.getSession().getAttribute(origem);
				
		Processo p = c.getProcessos().get(i);
		
		pedido.getSession().setAttribute("processoVisualizar", p);
		return "/Comum/verProcesso.jsp";
	}
}
