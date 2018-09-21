package web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContratoDAO;

public class TelaPrincipalPresidente implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		pedido.getSession().setAttribute("contratos", new ContratoDAO().getAll("nomeEmpresaContratada"));
		return "/Presidente/index.jsp";
	}
}
