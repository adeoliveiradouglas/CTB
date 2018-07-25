package logica;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CargoDAO;
import dao.SetorDAO;
import entity.Usuario;

public class TelaEditarUsuario implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		/*
		 * Recebe a posi��o que o usuario est� na lista de usu�rios 
		 */
		
		@SuppressWarnings("unchecked") 
		ArrayList<Usuario> lu = (ArrayList<Usuario>) pedido.getSession().getAttribute("usuarios");
				
		Usuario u = lu.get(Integer.parseInt(pedido.getParameter("id")));
		
		pedido.getSession().setAttribute("usuarioeditar", u);
		
		if(pedido.getSession().getAttribute("cargos") == null){
//			Para n�o repetir a opera��o de buscar no banco toda vez que abrir a p�gina
			pedido.getSession().setAttribute("cargos", new CargoDAO().getAll());
			pedido.getSession().setAttribute("setores", new SetorDAO().getAll());
		}
		
		return "/Administrador/editarusuario.jsp";
	}

}
