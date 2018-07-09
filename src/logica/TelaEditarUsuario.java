package logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import entity.Usuario;

public class TelaEditarUsuario implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		Usuario u = new UsuarioDAO().getById(
			Integer.parseInt(pedido.getParameter("id"))
		);
		
		pedido.getSession().setAttribute("usuarioeditar", u);
		
		return "/Administrador/editarusuario.jsp";
	}

}
