package web;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContratoDAO;
import dao.ProcessoDAO;
import entity.Contrato;
import entity.Processo;
import utilidades.FormatarCampo;

public class CadastrarProcesso implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		Contrato c = (Contrato) pedido.getSession().getAttribute("contratoVisualizar");
		Date dataProcesso = new SimpleDateFormat("yyyy-MM-dd").parse(
				pedido.getParameter("data")
			),
			novaDataVencimento;
		
		String v = new FormatarCampo().stringToDecimal(pedido.getParameter("valor")),
			   va = new FormatarCampo().stringToDecimal(pedido.getParameter("valorAditivo")),
			   notaFiscal = pedido.getParameter("notaFiscal"),
			   numero = pedido.getParameter("numero");
		
		BigDecimal valor = null, aditivo = null, saldo;
		
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
			//catch para n�o dar erro no processo quando n�o houver aditivo
			aditivo = new BigDecimal("0.00");
		}

		saldo = c.getSaldo().add(aditivo);
		saldo = saldo.subtract(saldo);
		
		
		Processo p = new Processo(
			notaFiscal,
			pedido.getParameter("tipoAditivo"),
			numero,
			pedido.getParameter("ano"),
			pedido.getParameter("mes"),
			aditivo,
			valor,
			saldo,
			dataProcesso,
			idContrato
		);
		
		new ProcessoDAO().inserir(p);
		
		//se houve atualiza��o na data de vencimento
		if (novaDataVencimento != null)
			new ContratoDAO().atualizarDataVencimento(idContrato, novaDataVencimento);
		
		return "sistema?logica=TelaPrincipalGestor";
	}
	
	
}
