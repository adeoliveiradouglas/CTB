/*
 * Redireciona para p�gina de ver determinado contrato
 * recebe dois parametros por GET
 * origem = lista carregada na sess�o em que o contrato est�
 * n = posicao do contrato na lista "origem"
 * */

package web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Cargo;
import entity.Contrato;

public class VerContrato implements Logica {

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		String origem = pedido.getParameter("origem"),
			   pagina = "/Comum/verContrato.jsp";
		int n;
		
		if (origem == null)
//			tenta saber qual lista de contrato deve acessar atrav�s do POST e guarda na sess�o
			origem = (String) pedido.getSession().getAttribute("origem");
		else		
//			se o POST falhar, � porque a informa��o est� na sess�o
			pedido.getSession().setAttribute("origem", origem);
		
		try {
			n = Integer.parseInt(pedido.getParameter("n"));
			pedido.getSession().setAttribute("n", n);
		} catch (Exception e) {
			n = (int) pedido.getSession().getAttribute("n");
		}
		
		
		Cargo logado = (Cargo) pedido.getSession().getAttribute("cargoParaLogin");
				
//		carrega a lista para essa p�gina
		@SuppressWarnings("unchecked")
		List<Contrato> contratosLista = (List<Contrato>) pedido.getSession().getAttribute(origem);
		
//		Pega o objeto contrato da lista 
		Contrato contrato = contratosLista.get(n);
		
//		para saber qual tela de contrato deve mostrar: a comum (somente visualiza) ou a do gestor (insere novos dados)
		if (logado.getId() == 3 || "true".equals(pedido.getParameter("adicionaProcesso"))){
			pagina = "/Gestor/verContrato.jsp"; 
		}

//		Coloca esses dados na sess�o
		pedido.getSession().setAttribute("contratoVisualizar", contrato);
		pedido.getSession().setAttribute("n", n);
		
		return pagina;
	}
}
