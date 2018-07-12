package logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import entity.Usuario;

public class TelaPrincipalAdministrador implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		Usuario u = (Usuario) pedido.getSession().getAttribute("usuario");
		
		
		pedido.getSession().setAttribute("usuariosnovos", new UsuarioDAO("usuariosnovos").getAll());
		pedido.getSession().setAttribute("usuarios", new UsuarioDAO().getAll());
		
		return "/" + u.getCargo().getNome() + "/index.jsp";
	}

}
