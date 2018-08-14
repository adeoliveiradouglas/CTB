package logica;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;

import dao.ProcessoDAO;
import entity.Processo;
import utilidades.FormatarCampo;

public class EditarPrevia implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		@SuppressWarnings("unchecked")
		ArrayList<Processo> previaProcessos = (ArrayList<Processo>) pedido.getSession().getAttribute("previaProcessos");
		
		int i = Integer.parseInt(pedido.getParameter("i"));
		String acao = pedido.getParameter("acao");
		
		if(acao.equals("remover")){
			previaProcessos.remove(i);
			
			pedido.getSession().setAttribute("previaProcessos", previaProcessos);		
			return "/Gestor/previaContrato.jsp";
		} else if (acao.equals("aprovar")){
			ProcessoDAO pdao = new ProcessoDAO();
			for (Processo p : previaProcessos){
				pdao.inserir(p);
			}
			return "sistema?logica=TelaPrincipalGestor";
		}else if (acao.equals("editar")){
			Date dataProcesso,
				novaDataVencimento;
			
			String v = new FormatarCampo().stringToDecimal(pedido.getParameter("valor")),
				   va = new FormatarCampo().stringToDecimal(pedido.getParameter("valorAditivo"));
			
			BigDecimal valor = null, aditivo = null;
						

			try {
				dataProcesso = new SimpleDateFormat("yyyy-MM-dd").parse(
						pedido.getParameter("data")
					);
				novaDataVencimento = new SimpleDateFormat("yyyy-MM-dd").parse(
					pedido.getParameter("novaDataVencimento")
				);
			} catch (Exception e){
				dataProcesso = null;
				novaDataVencimento = null;
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
			return "/Gestor/editarPreviaContrato.jsp";
		}  
	}

}
