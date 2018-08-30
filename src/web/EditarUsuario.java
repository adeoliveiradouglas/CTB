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
		ArrayList<Cargo> cargos = ((ArrayList<Cargo>) pedido.getSession().getAttribute("cargos"));
		ArrayList<Setor> setores = ((ArrayList<Setor>) pedido.getSession().getAttribute("setores"));
		int posicao = Integer.parseInt(pedido.getParameter("setorNovo"));
		Setor setor = setores.get(posicao);
		
		posicao = Integer.parseInt(pedido.getParameter("cargoNovo"));
		Cargo cargo = cargos.get(posicao);
		
		Usuario u = new Usuario(
			((Usuario) pedido.getSession().getAttribute("usuarioeditar")).getId(),
			Integer.parseInt(pedido.getParameter("matricula")), 
			pedido.getParameter("nome"),
			pedido.getParameter("email"), 
			((Usuario) pedido.getSession().getAttribute("usuarioeditar")).getSenha(), 
			setor, 
			cargo
		);
		
		new UsuarioDAO().atualizar(u);
		return "sistema?logica=TelaPrincipal";
	}

}
