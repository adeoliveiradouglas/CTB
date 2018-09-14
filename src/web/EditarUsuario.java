package web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dao.UsuarioDAO;
import entity.Cargo;
import entity.Setor;
import entity.Usuario;

public class EditarUsuario implements Logica{

	@Override
	@SuppressWarnings("unchecked")
	public String executa(HttpServletRequest pedido) throws Exception {
		ArrayList<Cargo> todosOsCargos = ((ArrayList<Cargo>) pedido.getSession().getAttribute("todososcargos")),
						 cargosDoUsuario = new ArrayList<Cargo>();
		ArrayList<Setor> todosOsSetores = ((ArrayList<Setor>) pedido.getSession().getAttribute("todosossetores"));
		String nome = pedido.getParameter("nome");
		int posicaoSetor = Integer.parseInt(pedido.getParameter("setorNovo")),
			posicaoCargo1 = Integer.parseInt(pedido.getParameter("cargoNovo0")),
			posicaoCargo2 = Integer.parseInt(pedido.getParameter("cargoNovo1"));
		
		Usuario usuarioEditar = (Usuario) pedido.getSession().getAttribute("usuarioeditar");
		Setor setor = null;
//		Quando o usuário não tem segundo cargo e não foi alterado, essa variável vem como -1
		if(posicaoCargo2 == -1)
			posicaoCargo2 = posicaoCargo1;
			
		if(posicaoSetor == -1)
			setor = usuarioEditar.getSetor();
		else
			setor = todosOsSetores.get(posicaoSetor);
		
//		Consertado bug que só aparecia o primeiro nome do usuário
		if(nome.equals(""))
			nome = usuarioEditar.getNome();
	
		cargosDoUsuario.add(todosOsCargos.get(posicaoCargo1));
		cargosDoUsuario.add(todosOsCargos.get(posicaoCargo2));
		
		Usuario u = new Usuario(
			((Usuario) pedido.getSession().getAttribute("usuarioeditar")).getId(),
			Integer.parseInt(pedido.getParameter("matricula")), 
			nome,
			pedido.getParameter("email"), 
			((Usuario) pedido.getSession().getAttribute("usuarioeditar")).getSenha(), 
			setor, 
			cargosDoUsuario
		);
		
		new UsuarioDAO().atualizar(u);
		return "sistema?logica=TelaPrincipal";
	}

}
