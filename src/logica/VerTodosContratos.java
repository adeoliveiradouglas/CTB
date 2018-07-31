package logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContratoDAO;
import entity.Usuario;

public class VerTodosContratos implements Logica {

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		String ordenacao = pedido.getParameter("ord");
		pedido.getSession().setAttribute("contratos", new ContratoDAO().getAll(ordenacao));
		return "/" + ((Usuario) pedido.getSession().getAttribute("usuario")).getCargo().getNome() + "/todosContratos.jsp";
	}

}
