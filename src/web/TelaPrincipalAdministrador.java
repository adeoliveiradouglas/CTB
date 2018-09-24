package web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;

public class TelaPrincipalAdministrador implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		pedido.setAttribute("usuariosnovos", new UsuarioDAO(true).getAll());
		pedido.setAttribute("usuarios", new UsuarioDAO().getAll());
		
		return "/Administrador/index.jsp";
	}

}
