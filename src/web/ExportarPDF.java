package web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Contrato;
import utilidades.PDF;

public class ExportarPDF implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		Contrato contrato = (Contrato) pedido.getSession().getAttribute("contratoVisualizar");
		String caminho = pedido.getServletContext().getRealPath("");
		
		new PDF(caminho).relatorioContrato(contrato);
		
		pedido.setAttribute("relatorioGerado", true);
		return "sistema?logica=VerContrato";
	}
}