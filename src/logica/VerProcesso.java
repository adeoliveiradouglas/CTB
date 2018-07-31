package logica;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Contrato;
import entity.Processo;

public class VerProcesso implements Logica {

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		String origem = pedido.getParameter("origem");
		int n = Integer.parseInt(pedido.getParameter("idContrato")),
				i = Integer.parseInt(pedido.getParameter("i")),
				aux;
		
		@SuppressWarnings("unchecked")
		ArrayList<Contrato> contratosLista = (ArrayList<Contrato>) pedido.getSession().getAttribute(origem);
		
		Contrato c = contratosLista.get(n);
		
		Processo p = c.getProcessos().get(i);
		
		pedido.getSession().setAttribute("processoVisualizar", p);
		return "/Comum/verProcesso.jsp";
	}
}
