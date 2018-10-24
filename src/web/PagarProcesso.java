/*
 * Recebe a ação por parâmetro. A ação pode ser "pagar" ou "tela":
 * Pagar: vem da tela de pagamento junto com a data escolhida pelo tesoureiro.
 * Tela: redireciona para a tela em que escolhe a data.
 */

package web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DadosDAO;
import entity.Contrato;
import entity.Usuario;

public class PagarProcesso implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		String acao = "" + pedido.getParameter("acao");
		int posicaoDados = Integer.parseInt(pedido.getParameter("i"));
		
		if(acao.equals("pagar")) {
			
			//Pega o objeto contrato da lista 
			Contrato contrato = (Contrato) pedido.getSession().getAttribute("contratoVisualizar");
					
			int idDados = contrato.getDados().get(posicaoDados).getId();
			int idTesoureiro = ((Usuario) pedido.getSession().getAttribute("usuario")).getId();
					
			//atualiza pagamento
			new DadosDAO().atualizarPagamento(idDados, idTesoureiro);
			
			//atualiza o contrato na lista de contratos
			contrato.getDados().remove(posicaoDados);
		
			pedido.getSession().getAttribute("contratoVisualizar");
			return "sistema?logica=VerContratoParaPagamento";
		}
		
		return "/Tesoureiro/pagarContrato.jsp";
	}

}
