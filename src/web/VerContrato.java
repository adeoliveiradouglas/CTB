/*
 * Redireciona para página de ver determinado contrato
 * recebe dois parametros por GET
 * origem = lista carregada na sessão em que o contrato está
 * n = posicao do contrato na lista "origem"
 * */

package web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Contrato;

public class VerContrato implements Logica {

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		String adicionaProcesso = "", //quando o usuário tem o poder de adicionar processos no contrato
			   origem = pedido.getParameter("origem"),
			   pagina = "/Comum/verContrato.jsp";
		int n = Integer.parseInt(pedido.getParameter("n"));
		
		//carrega a lista para essa página
		@SuppressWarnings("unchecked")
		List<Contrato> contratosLista = (List<Contrato>) pedido.getSession().getAttribute(origem);
		
		//Pega o objeto contrato da lista 
		Contrato contrato = contratosLista.get(n);
		
		//para saber qual tela de contrato deve mostrar: a comum (somente visualiza) ou a do gestor (insere novos processos)
		if ((String) pedido.getParameter("adicionaProcesso") != null){
			pagina = "/Gestor/verContrato.jsp"; 
		}

		/*Coloca esses dados na sessão*/
		pedido.getSession().setAttribute("contratoVisualizar", contrato);
		pedido.getSession().setAttribute("origem", origem);
		pedido.getSession().setAttribute("n", n);
		pedido.getSession().setAttribute("adicionaProcesso", adicionaProcesso);
		
		return pagina;
	}
}
