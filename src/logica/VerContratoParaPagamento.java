package logica;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Contrato;

public class VerContratoParaPagamento implements Logica {

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		String origem = pedido.getParameter("origem");
		int n = Integer.parseInt(pedido.getParameter("n"));
		
		
		@SuppressWarnings("unchecked")
		ArrayList<Contrato> contratosLista = (ArrayList<Contrato>) pedido.getSession().getAttribute(origem);

		//Pega o objeto contrato da lista 
		Contrato contrato = contratosLista.get(n);
		
		pedido.getSession().setAttribute("contratoVisualizar", contrato);
		return "/Tesoureiro/verContrato.jsp";
	}
}
