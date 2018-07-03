package logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TelaEditarUsuario implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		String e = pedido.getParameter("email");
		pedido.getSession().setAttribute("email", e);
		
		return "/Administrador/editarusuario.jsp";
	}

}
