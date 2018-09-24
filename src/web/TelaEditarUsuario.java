package web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CargoDAO;
import dao.SetorDAO;
import entity.Cargo;
import entity.Setor;
import entity.Usuario;

public class TelaEditarUsuario implements Logica{

	private static List<Cargo> todosOsCargos = null;
	private static List<Setor> todosOsSetores = null;
	
	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		/*
		 * Recebe a posi��o que o usuario est� na lista de usu�rios 
		 */
		
		@SuppressWarnings("unchecked") 
		List<Usuario> lu = (ArrayList<Usuario>) pedido.getSession().getAttribute("usuarios");
				
		Usuario u = lu.get(Integer.parseInt(pedido.getParameter("id")));
		
		pedido.setAttribute("usuarioeditar", u);
		
		if(todosOsCargos == null || todosOsSetores == null){
//			Para n�o repetir a opera��o de buscar no banco toda vez que abrir a p�gina
			todosOsCargos = new CargoDAO().getAll();
			todosOsSetores = new SetorDAO().getAll();
		}
		
		pedido.setAttribute("todosossetores", todosOsSetores);
		pedido.setAttribute("todososcargos", todosOsCargos);
		
		return "/Administrador/editarusuario.jsp";
	}

}
