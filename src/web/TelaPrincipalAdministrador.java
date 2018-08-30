package web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import entity.Usuario;

public class TelaPrincipalAdministrador implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		Usuario u = (Usuario) pedido.getSession().getAttribute("usuario");
		String ordenacao = pedido.getParameter("ordUser");
		
		pedido.getSession().setAttribute("usuariosnovos", new UsuarioDAO("usuariosnovos").getAllOrdenado(ordenacao));
		pedido.getSession().setAttribute("usuarios", new UsuarioDAO().getAllOrdenado(ordenacao));
		
		return "/" + u.getCargo().getNome() + "/index.jsp";
	}

}
