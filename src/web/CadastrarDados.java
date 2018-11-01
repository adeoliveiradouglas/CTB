package web;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContratoDAO;
import dao.DadosDAO;
import entity.Contrato;
import entity.Dados;
import utilidades.FormatarCampo;

public class CadastrarDados implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		Contrato c = (Contrato) pedido.getSession().getAttribute("contratoVisualizar");
		Date dataDados = new SimpleDateFormat("yyyy-MM-dd").parse(
				pedido.getParameter("data")
			),
			novaDataVencimento;
		
		String v = new FormatarCampo().stringToDecimal(pedido.getParameter("valor")),
			   va = new FormatarCampo().stringToDecimal(pedido.getParameter("valorAditivo")),
			   notaFiscal = pedido.getParameter("notaFiscal"),
			   numero = pedido.getParameter("numero");
		
		BigDecimal valor = null, 
				   aditivo = null;
		
		int idContrato = Integer.parseInt(pedido.getParameter("idContrato"));

		try {
			novaDataVencimento = new SimpleDateFormat("yyyy-MM-dd").parse(
				pedido.getParameter("novaDataVencimento")
			);
		} catch (Exception e){
			novaDataVencimento = null;
		}
		
		try{
			valor = new BigDecimal(v); 
			aditivo = new BigDecimal(va);
		} catch (NumberFormatException e){
			//catch para não dar erro no processo quando não houver aditivo
			aditivo = new BigDecimal("0.00");
		}
		
		Dados p = new Dados(
			notaFiscal,
			pedido.getParameter("tipoAditivo"),
			numero,
			Integer.parseInt(pedido.getParameter("ano")),
			pedido.getParameter("mes"),
			aditivo,
			valor,
			null, //saldo é calculado mais abaixo
			dataDados,
			idContrato
		);
		
//		Adiciona o dado na lista do contrato e recalcula os saldos
		int posicao = c.addDados(p);
		
//		Busca o saldo calculado, atualiza o objeto para mandar inserir no banco
		p.setSaldo(
			c.getDados().get(
				posicao
			).getSaldo()
		);
		
		new DadosDAO().inserir(p);
		
		pedido.getSession().setAttribute("contratoVisualizar", c);
		
		//se houve atualização na data de vencimento
		if (novaDataVencimento != null)
			new ContratoDAO().atualizarDataVencimento(idContrato, novaDataVencimento);
		
		return "sistema?logica=VerContrato";
	}
	
	
}
