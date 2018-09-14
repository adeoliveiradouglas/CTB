package web;

import javax.servlet.http.HttpServletRequest;

import dao.CargoDAO;
import dao.SetorDAO;

public class TelaCadastroUsuario implements Logica{

	@Override
	public String executa(HttpServletRequest pedido) throws Exception {
		pedido.getSession().setAttribute("setor", new SetorDAO().getAll());
		pedido.getSession().setAttribute("cargo", new CargoDAO().getAll());
		
		return "/cadastrousuario.jsp";
	}

}
