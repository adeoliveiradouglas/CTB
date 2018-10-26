package web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContratoDAO;

public class TelaPrincipalGestorGeral implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		pedido.getSession().setAttribute("contratosRecentes", new ContratoDAO().getAllRecente(5));
		pedido.getSession().setAttribute("contratos90", new ContratoDAO().getVencimento90());
		
		return "/Gestor geral/index.jsp";
	}
}
