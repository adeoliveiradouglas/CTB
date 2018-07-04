package logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import entity.Usuario;

public class TelaEditarUsuario implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		Usuario u = new UsuarioDAO().getByEmail(
			pedido.getParameter("email")
		);
		
		pedido.getSession().setAttribute("usuarioeditar", u);
		
		return "/Administrador/editarusuario.jsp";
	}

}
