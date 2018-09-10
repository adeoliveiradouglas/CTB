package web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import entity.Cargo;
import entity.Setor;
import entity.Usuario;

public class EditarUsuario implements Logica{

	@Override
	@SuppressWarnings("unchecked")
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		ArrayList<Cargo> todosOsCargos = ((ArrayList<Cargo>) pedido.getSession().getAttribute("todososcargos")),
						 cargosDoUsuario = new ArrayList<Cargo>();
		ArrayList<Setor> todosOsSetores = ((ArrayList<Setor>) pedido.getSession().getAttribute("todosossetores"));
		int posicao = Integer.parseInt(pedido.getParameter("setorNovo"));
		Setor setor = todosOsSetores.get(posicao);
		
		posicao = Integer.parseInt(pedido.getParameter("cargoNovo0"));
		Cargo cargo = todosOsCargos.get(posicao);
		
		posicao = Integer.parseInt(pedido.getParameter("cargoNovo1"));
		cargosDoUsuario.add(cargo);
		Usuario u = new Usuario(
			((Usuario) pedido.getSession().getAttribute("usuarioeditar")).getId(),
			Integer.parseInt(pedido.getParameter("matricula")), 
			pedido.getParameter("nome"),
			pedido.getParameter("email"), 
			((Usuario) pedido.getSession().getAttribute("usuarioeditar")).getSenha(), 
			setor, 
			cargosDoUsuario
		);
		
		new UsuarioDAO().atualizar(u);
		return "sistema?logica=TelaPrincipal";
	}

}
