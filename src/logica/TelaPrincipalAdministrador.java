package logica;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import entity.Usuario;

public class TelaPrincipalAdministrador implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		Usuario u = (Usuario) pedido.getSession().getAttribute("usuario");
		
		ArrayList<Usuario> lunovos = new UsuarioDAO("usuariosnovos").getAll(); 
		
		pedido.getSession().setAttribute("usuariosnovos", lunovos);
		
		return "/" + u.getCargo() + "/index.jsp";
	}

}
