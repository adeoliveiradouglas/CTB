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

import entity.Cargo;
import entity.Contrato;

public class VerContrato implements Logica {

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		String origem = pedido.getParameter("origem"),
			   pagina = "/Comum/verContrato.jsp";
		int n;
		
		if (origem == null)
//			tenta saber qual lista de contrato deve acessar através do POST e guarda na sessão
			origem = (String) pedido.getSession().getAttribute("origem");
		else		
//			se o POST falhar, é porque a informação está na sessão
			pedido.getSession().setAttribute("origem", origem);
		
		try {
			n = Integer.parseInt(pedido.getParameter("n"));
			pedido.getSession().setAttribute("n", n);
		} catch (Exception e) {
			n = (int) pedido.getSession().getAttribute("n");
		}
		
		
		Cargo logado = (Cargo) pedido.getSession().getAttribute("cargoParaLogin");
				
//		carrega a lista para essa página
		@SuppressWarnings("unchecked")
		List<Contrato> contratosLista = (List<Contrato>) pedido.getSession().getAttribute(origem);
		
//		Pega o objeto contrato da lista 
		Contrato contrato = contratosLista.get(n);
		
//		para saber qual tela de contrato deve mostrar: a comum (somente visualiza) ou a do gestor (insere novos dados)
		if (logado.getId() == 3 && "true".equals(pedido.getParameter("adicionaProcesso"))){
			pagina = "/Gestor/verContrato.jsp"; 
		}

//		Coloca esses dados na sessão
		pedido.getSession().setAttribute("contratoVisualizar", contrato);
		pedido.getSession().setAttribute("n", n);
		
		return pagina;
	}
}
