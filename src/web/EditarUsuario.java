package web;


import java.util.ArrayList;
import java.util.List;

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
		List<Setor> todosOsSetores = ((List<Setor>) pedido.getSession().getAttribute("todosossetores"));
		List<Cargo> todosOsCargos = ((List<Cargo>) pedido.getSession().getAttribute("todososcargos")),
					cargosDoUsuario = new ArrayList<Cargo>();
		int posicaoSetor = Integer.parseInt(pedido.getParameter("setorNovo")),
			posicaoCargo1 = Integer.parseInt(pedido.getParameter("cargoNovo0")),
			posicaoCargo2 = Integer.parseInt(pedido.getParameter("cargoNovo1"));
		Setor setor = null;
		
//		Quando o usuário não tem segundo cargo e não foi alterado, essa variável vem como -1
		if(posicaoCargo2 == -1)
			posicaoCargo2 = posicaoCargo1;
			
		setor = todosOsSetores.get(posicaoSetor);
		
		cargosDoUsuario.add(todosOsCargos.get(posicaoCargo1));
		cargosDoUsuario.add(todosOsCargos.get(posicaoCargo2));
		
		Usuario u = new Usuario(
			Integer.parseInt(pedido.getParameter("id")),
			Integer.parseInt(pedido.getParameter("matricula")), 
			pedido.getParameter("nome"),
			pedido.getParameter("email"), 
			pedido.getParameter("senha"), 
			setor, 
			cargosDoUsuario
		);
		
		new UsuarioDAO().atualizar(u);
		return "sistema?logica=TelaPrincipalAdministrador";
	}

}
