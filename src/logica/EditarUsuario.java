package logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import entity.Usuario;

public class EditarUsuario implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		Usuario u = new Usuario();
		
		u.setNome(pedido.getParameter("nome"));
		u.setMatricula(Integer.parseInt(pedido.getParameter("matricula")));
		u.setCargo(pedido.getParameter("cargo"));
		u.setSetor(pedido.getParameter("setor"));
		u.setEmail(pedido.getParameter("email"));
		u.setSenha(
			((Usuario) pedido.getSession().getAttribute("usuarioeditar")).getSenha()	
		);
		
		new UsuarioDAO().atualizar(u);
		return "sistema?logica=TelaPrincipal";
	}

}
