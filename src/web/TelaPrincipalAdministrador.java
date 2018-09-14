package web;

import javax.servlet.http.HttpServletRequest;

import dao.UsuarioDAO;

public class TelaPrincipalAdministrador implements Logica{

	@Override
	public String executa(HttpServletRequest pedido) throws Exception {
		String ordenacao = pedido.getParameter("ordUser");
		
		pedido.getSession().setAttribute("usuariosnovos", new UsuarioDAO(true).getAll(ordenacao));
		pedido.getSession().setAttribute("usuarios", new UsuarioDAO().getAll(ordenacao));
		
		return "/Administrador/index.jsp";
	}

}
