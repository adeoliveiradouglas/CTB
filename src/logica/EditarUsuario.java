package logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CargoDAO;
import dao.SetorDAO;
import dao.UsuarioDAO;
import entity.Usuario;

public class EditarUsuario implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		Usuario u = new Usuario(
			((Usuario) pedido.getSession().getAttribute("usuarioeditar")).getId(),
			Integer.parseInt(pedido.getParameter("matricula")), 
			pedido.getParameter("nome"),
			pedido.getParameter("email"), 
			((Usuario) pedido.getSession().getAttribute("usuarioeditar")).getSenha(), 
			new SetorDAO().getByCodigo(pedido.getParameter("setor")), 
			new CargoDAO().getById(Integer.parseInt(pedido.getParameter("cargo")))
		);
		
		new UsuarioDAO().atualizar(u);
		return "sistema?logica=TelaPrincipal";
	}

}
