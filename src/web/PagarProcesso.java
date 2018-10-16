package web;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContratoDAO;
import dao.DadosDAO;
import entity.Contrato;
import entity.Usuario;

public class PagarProcesso implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		String origemContrato = "" + pedido.getSession().getAttribute("origemContrato");
		int posicaoContrato = Integer.parseInt("" + pedido.getSession().getAttribute("posicaoNaOrigem"));
		
		@SuppressWarnings("unchecked")
		List<Contrato> contratosLista = (List<Contrato>) pedido.getSession().getAttribute(origemContrato);

		//Pega o objeto contrato da lista 
		Contrato contrato = contratosLista.get(posicaoContrato);
				
		int idDados = Integer.parseInt(pedido.getParameter("idDados"));
		int idTesoureiro = ((Usuario) pedido.getSession().getAttribute("usuario")).getId();
				
		//atualiza pagamento
		new DadosDAO().atualizarPagamento(idDados, idTesoureiro);
		
		//atualiza o contrato na lista de contratos
		contratosLista.remove(posicaoContrato);
		contratosLista.add(posicaoContrato, new ContratoDAO().getByIdSemPagamento(contrato.getId()));
		
		return "sistema?logica=VerContratoParaPagamento";
	}

}
