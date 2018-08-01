/*
 * Redireciona para p�gina de ver determinado contrato
 * recebe dois parametros por GET
 * origem = lista carregada na sess�o em que o contrato est�
 * n = posicao do contrato na lista "origem"
 * */

package logica;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Contrato;

public class VerContrato implements Logica {

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		String 
			   adicionaProcesso = "", //quando o usu�rio tem o poder de adicionar processos no contrato
			   origem = pedido.getParameter("origem");
		int n = Integer.parseInt(pedido.getParameter("n"));
		
		//carrega a lista para essa p�gina
		@SuppressWarnings("unchecked")
		ArrayList<Contrato> contratosLista = (ArrayList<Contrato>) pedido.getSession().getAttribute(origem);
		
		//Pega o objeto contrato da lista 
		Contrato contrato = contratosLista.get(n);
		
		if ((String) pedido.getParameter("adicionaProcesso") != null){
			adicionaProcesso = (String) pedido.getParameter("adicionaProcesso");
		}
		
		pedido.getSession().setAttribute("contratoVisualizar", contrato);
		pedido.setAttribute("adicionaProcesso", adicionaProcesso);
		
		return "/Comum/verContrato.jsp";
	}
}
