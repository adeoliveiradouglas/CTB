package web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import entity.Contrato;

public class VerContratoParaPagamento implements Logica {

	@Override
	public String executa(HttpServletRequest pedido) throws Exception {
		String origemContrato;
		int posicaoContrato;
		
		try{
			origemContrato = pedido.getParameter("origemContrato");
			posicaoContrato = Integer.parseInt(pedido.getParameter("n"));
		} catch (Exception e){
			origemContrato = "" + pedido.getSession().getAttribute("origemContrato");
			posicaoContrato = Integer.parseInt("" + pedido.getSession().getAttribute("posicaoNaOrigem"));
		}
		
		pedido.getSession().setAttribute("origemContrato", origemContrato);
		pedido.getSession().setAttribute("posicaoNaOrigem", posicaoContrato);
		
		@SuppressWarnings("unchecked")
		ArrayList<Contrato> contratosLista = (ArrayList<Contrato>) pedido.getSession().getAttribute(origemContrato);

		//Pega o objeto contrato da lista 
		Contrato contrato = contratosLista.get(posicaoContrato);
		
		pedido.getSession().setAttribute("contratoVisualizar", contrato);
		return "/Tesoureiro/verContrato.jsp";
	}
}
