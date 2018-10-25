/*
 * Recebe a ação por parâmetro. A ação pode ser "pagar" ou "tela":
 * Pagar: vem da tela de pagamento junto com a data escolhida pelo tesoureiro.
 * Tela: redireciona para a tela em que escolhe a data.
 */

package web;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;

import dao.DadosDAO;
import entity.Contrato;
import entity.Usuario;

public class PagarProcesso implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		String acao = pedido.getParameter("acao");
		int posicaoDados = 0;
		
		try {
			posicaoDados = Integer.parseInt(pedido.getParameter("i"));
			pedido.getSession().setAttribute("i", posicaoDados);
		}catch(Exception e){			
			posicaoDados = (int) pedido.getSession().getAttribute("i");
		}
		
		if(acao.equals("pagar")) {
			
			//Pega o objeto contrato da lista 
			Contrato contrato = (Contrato) pedido.getSession().getAttribute("contratoVisualizar");
					
			int idDados = Integer.parseInt(pedido.getParameter("id"));
			int idTesoureiro = ((Usuario) pedido.getSession().getAttribute("usuario")).getId();
			
			final String formatoData = "yyyy-MM-dd";
			Date pagamento = new SimpleDateFormat(formatoData).parse(pedido.getParameter("dataPagamento"));
					
			//atualiza pagamento
			new DadosDAO().atualizarPagamento(idDados, idTesoureiro, new DateTime(pagamento));
			
			//atualiza o contrato na lista de contratos
			contrato.getDados().remove(posicaoDados);
		
			pedido.getSession().getAttribute("contratoVisualizar");
			return "sistema?logica=VerContratoParaPagamento";
		}
		
		
		
		return "/Tesoureiro/pagarProcesso.jsp";
	}

}
