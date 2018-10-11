package web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CargoDAO;
import dao.SetorDAO;

public class TelaCadastroUsuario implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		pedido.getSession().setAttribute("setores", new SetorDAO().getAll());
		pedido.getSession().setAttribute("cargos", new CargoDAO().getAll());
		
		
		return "/cadastrousuario.jsp";
	}

}
