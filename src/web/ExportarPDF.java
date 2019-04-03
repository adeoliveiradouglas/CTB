package web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContratoDAO;
import utilidades.PDF;

public class ExportarPDF implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		new PDF(pedido.getContextPath()).relatorioContrato(new ContratoDAO().getAll().get(0));
		return "";
	}
}