package web;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;

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
			   numero = pedido.getParameter("numero"),
			   ano = pedido.getParameter("ano");
		
		BigDecimal valor = null, 
				   aditivo = null;
		
		int idContrato = Integer.parseInt(pedido.getParameter("idContrato"));
		
		try {
			novaDataVencimento = new SimpleDateFormat("yyyy-MM-dd").parse(
				pedido.getParameter("novaDataVencimento")
			);
			
			c.setDataVencimentoContrato(new DateTime(novaDataVencimento));
		} catch (Exception e){
			novaDataVencimento = null;
		}
		
		try{
			valor = new BigDecimal(v); 
			aditivo = new BigDecimal(va);
			
			c.setValorAditivos(
				c.getValorAditivos().add(aditivo)
			);
		} catch (NumberFormatException e){
			//catch para não dar erro no processo quando não houver aditivo
			aditivo = new BigDecimal("0.00");
		}
		
//		GAMBIARRA PQ TO COM PREGUIÇA DE PROGRAMAR 
		if(ano.length() == 2) 
//			caso o usuário seja jumento o suficiente pra escrever 19 ao invés de 2019 (baseado em fatos reais)
			ano = "20"+ano;
		 else if (ano.length() == 3) 
//			caso o usuário seja alignigena o suficiente pra escrever 019 ao invés de 2019 (aqui já to sendo escroto 
//			mesmo). O se por um milagre esse sistema fique ativo até 2999. Pelo menos tá garantido.
			ano = "2"+ano;
		
		Dados p = new Dados(
			notaFiscal,
			pedido.getParameter("tipoAditivo"),
			numero,
			Integer.parseInt(ano) ,
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
		
		pedido.setAttribute("adicionaProcesso", "true");
		return "sistema?logica=VerContrato&adicionaProcesso=true";
	}
}