package web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Contrato;
import entity.Dados;

public class VerDados implements Logica {

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		int i = Integer.parseInt(pedido.getParameter("i"));
		String origem = pedido.getParameter("origem"); 
		Contrato c = (Contrato) pedido.getSession().getAttribute(origem);
				
		Dados p = c.getDados().get(i);
		
		pedido.setAttribute("dadosVisualizar", p);
		return "/Comum/verDados.jsp";
	}
}
