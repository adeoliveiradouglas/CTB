package web;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;

import dao.ContratoDAO;
import dao.ProcessoDAO;
import entity.Processo;
import utilidades.FormatarCampo;

public class EditarPrevia implements Logica{

	@SuppressWarnings("unchecked")
	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		ArrayList<Processo> previaProcessos = (ArrayList<Processo>) pedido.getSession().getAttribute("previaProcessos");
		
//		indice da lista do processo que está sendo manipulado
		int i;
		
//		recebe o que deve ser feito
		String acao = pedido.getParameter("acao");
		
		if(acao.equals("remover")){
			i = Integer.parseInt(pedido.getParameter("i"));
			
//			remove processo da lista previa
			((ArrayList<Processo>) pedido.getSession().getAttribute("previaProcessos")).remove(i);
			
			//pedido.getSession().setAttribute("previaProcessos", previaProcessos);		
			return "/Gestor/previaContrato.jsp";
			
		} else if (acao.equals("aprovar")){
			Date novaDataVencimento;
			
			try {
				novaDataVencimento = new SimpleDateFormat("yyyy-MM-dd").parse(
					pedido.getParameter("novaDataVencimento")
				);
			} catch (Exception e){
				novaDataVencimento = null;
			}
			
			//se houve atualização na data de vencimento
			if (novaDataVencimento != null)
				new ContratoDAO().atualizarDataVencimento(previaProcessos.get(0).getIdContrato(), novaDataVencimento);
			
			
//			grava todos os dados no banco
			for (Processo p : previaProcessos){
				new ProcessoDAO().inserir(p);
			}
			
			return "sistema?logica=TelaPrincipalGestor";
			
		}else if (acao.equals("editar")){
//			altera um processo da lista previa
			
			Date dataProcesso;
			
			i = Integer.parseInt(pedido.getParameter("i"));
			
			String v = new FormatarCampo().stringToDecimal(pedido.getParameter("valor")),
				   va = new FormatarCampo().stringToDecimal(pedido.getParameter("valorAditivo"));
			
			BigDecimal valor = null, aditivo = null;
						
			try {
				dataProcesso = new SimpleDateFormat("yyyy-MM-dd").parse(
					pedido.getParameter("data")
				);
			} catch (Exception e){
				dataProcesso = null;
			}
			
			try{
				valor = new BigDecimal(v); 
			} catch (NumberFormatException e){
				//catch para não dar erro no processo quando não houver aditivo
				valor = new BigDecimal("0.00");
			}
			
			try{
				valor = new BigDecimal(v); 
				aditivo = new BigDecimal(va);
			} catch (NumberFormatException e){
				//catch para não dar erro no processo quando não houver aditivo
				aditivo = new BigDecimal("0.00");
			}
			
			previaProcessos.get(i).setNotaFiscal(pedido.getParameter("notaFiscal"));
			previaProcessos.get(i).setTipoAditivo(pedido.getParameter("tipoAditivo"));
			previaProcessos.get(i).setNumeroSei(pedido.getParameter("numero"));
			previaProcessos.get(i).setAno(pedido.getParameter("ano"));
			previaProcessos.get(i).setMes(pedido.getParameter("mes"));
			previaProcessos.get(i).setAditivo(aditivo);
			previaProcessos.get(i).setValor(valor);
			previaProcessos.get(i).setDataProcesso(new DateTime(dataProcesso));
			
			pedido.getSession().setAttribute("previaProcessos", previaProcessos);
			return "/Gestor/previaContrato.jsp";
			
		} else {		
//			envia para a página de editar dados de um processo
			return "/Gestor/editarPreviaContrato.jsp";
		}  
	}

}
