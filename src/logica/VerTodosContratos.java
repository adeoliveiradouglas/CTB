package logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContratoDAO;

public class VerTodosContratos implements Logica {

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		pedido.getSession().setAttribute("contratos", new ContratoDAO().getAll("nomeEmpresaContratada"));
		return "/Gestor geral/todosContratos.jsp";
	}
}
