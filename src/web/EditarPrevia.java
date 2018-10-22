package web;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;

import dao.ContratoDAO;
import dao.DadosDAO;
import entity.Contrato;
import entity.Dados;
import utilidades.FormatarCampo;

public class EditarPrevia implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		List<Dados> previaDados = ((Contrato) pedido.getSession().getAttribute("contratoVisualizar")).getDados();
		
//		indice da lista do processo que está sendo manipulado
		int i; 
		
//		recebe o que deve ser feito
		String acao = pedido.getParameter("acao");
		
		if(acao.equals("remover")){
			i = Integer.parseInt(pedido.getParameter("i"));
			
//			remove processo da lista previa
			previaDados.remove(i);
			
			for(int aux = i; aux < previaDados.size(); ++aux) {
				
			}
			
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
				new ContratoDAO().atualizarDataVencimento(previaDados.get(0).getIdContrato(), novaDataVencimento);
			
			
//			grava todos os dados no banco
			for (Dados p : previaDados){
				new DadosDAO().inserir(p);
			}
			
			return "sistema?logica=TelaPrincipalGestor";
			
		}else if (acao.equals("editar")){
//			altera um processo da lista previa
			
			i = Integer.parseInt(pedido.getParameter("i"));
			
			Date dataDados;
			
			String v = new FormatarCampo().stringToDecimal(pedido.getParameter("valor")),
				   va = new FormatarCampo().stringToDecimal(pedido.getParameter("valorAditivo"));
			
			BigDecimal valor = null, aditivo = null;
						
			try {
				dataDados = new SimpleDateFormat("yyyy-MM-dd").parse(
					pedido.getParameter("data")
				);
			} catch (Exception e){
				dataDados = null;
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
			
			previaDados.get(i).setNotaFiscal(pedido.getParameter("notaFiscal"));
			previaDados.get(i).setTipoAditivo(pedido.getParameter("tipoAditivo"));
			previaDados.get(i).setNumeroSei(pedido.getParameter("numero"));
			previaDados.get(i).setAno(pedido.getParameter("ano"));
			previaDados.get(i).setMes(pedido.getParameter("mes"));
			previaDados.get(i).setAditivo(aditivo);
			previaDados.get(i).setValor(valor);
			previaDados.get(i).setData(new DateTime(dataDados));			
		} else {		
//			envia para a página de editar dados de um processo
			i = Integer.parseInt(pedido.getParameter("i"));
			
			Dados p = previaDados.get(i);
			pedido.setAttribute("processoEditar", p);
			pedido.setAttribute("i", i);
			return "/gestaodecontratos/Gestor/editarPreviaContrato.jsp";
		}  
		
		((Contrato) pedido.getSession().getAttribute("contratoVisualizar")).setDados(previaDados);
		return "/Gestor/previaContrato.jsp";
	}

}
