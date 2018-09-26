package web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CargoDAO;
import dao.SetorDAO;
import entity.Cargo;
import entity.Setor;
import entity.Usuario;

public class TelaEditarUsuario implements Logica{

	
	
	@SuppressWarnings("unchecked")
	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		/*
		 * Recebe a posição que o usuario está na lista de usuários 
		 */
		
		List<Cargo> todosOsCargos = (List<Cargo>) pedido.getSession().getAttribute("todosossetores");
		List<Setor> todosOsSetores = (List<Setor>) pedido.getSession().getAttribute("todososcargos");
		List<Usuario> lu = (List<Usuario>) pedido.getSession().getAttribute("usuarios");
				
		Usuario u = lu.get(Integer.parseInt(pedido.getParameter("posicao")));
		
		if(todosOsCargos == null || todosOsSetores == null){
//			Para não repetir a operação de buscar no banco toda vez que abrir a página
			todosOsCargos = new CargoDAO().getAll();
			todosOsSetores = new SetorDAO().getAll();
		}
		
		int posicaoSetor = todosOsSetores.indexOf(u.getSetor());
		
		pedido.setAttribute("usuarioeditar", u);
		pedido.setAttribute("posicaoSetor", posicaoSetor);
		pedido.setAttribute("posicaoCargo0", todosOsCargos.indexOf(u.getCargo().get(0)));
		pedido.setAttribute("posicaoCargo1", todosOsCargos.indexOf(u.getCargo().get(1)));
		pedido.getSession().setAttribute("todosossetores", todosOsSetores);
		pedido.getSession().setAttribute("todososcargos", todosOsCargos);
		
		return "/Administrador/editarusuario.jsp";
	}

}
