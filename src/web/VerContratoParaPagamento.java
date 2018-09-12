package web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Contrato;

public class VerContratoParaPagamento implements Logica {

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		String origem;
		int n;
		
		try{
			origem = pedido.getParameter("origem");
			n = Integer.parseInt(pedido.getParameter("n"));
		} catch (Exception e){
			origem = "" + pedido.getSession().getAttribute("origem");
			n = Integer.parseInt("" + pedido.getSession().getAttribute("posicaoNaOrigem"));
		}
		
		pedido.getSession().setAttribute("origem", origem);
		pedido.getSession().setAttribute("posicaoNaOrigem", n);
		
		@SuppressWarnings("unchecked")
		ArrayList<Contrato> contratosLista = (ArrayList<Contrato>) pedido.getSession().getAttribute(origem);

		//Pega o objeto contrato da lista 
		Contrato contrato = contratosLista.get(n);
		
		pedido.getSession().setAttribute("contratoVisualizar", contrato);
		return "/Tesoureiro/verContrato.jsp";
	}
}
