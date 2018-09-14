package web;

import javax.servlet.http.HttpServletRequest;

import dao.ContratoDAO;

public class VerTodosContratos implements Logica {

	@Override
	public String executa(HttpServletRequest pedido) throws Exception {
		pedido.getSession().setAttribute("contratos", new ContratoDAO().getAll("nomeEmpresaContratada"));
		return "/Gestor geral/todosContratos.jsp";
	}
}
