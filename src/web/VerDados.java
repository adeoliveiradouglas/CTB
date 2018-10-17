package web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Contrato;
import entity.Dados;

public class VerDados implements Logica {

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		int i = Integer.parseInt(pedido.getParameter("i"));
		
		Contrato c = (Contrato) pedido.getSession().getAttribute("contratoVisualizar");
				
		Dados p = c.getProcessos().get(i);
		
		pedido.getSession().setAttribute("dadosVisualizar", p);
		return "/Comum/verDados.jsp";
	}
}
