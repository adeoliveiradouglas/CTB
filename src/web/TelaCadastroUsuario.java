package web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CargoDAO;
import dao.SetorDAO;

public class TelaCadastroUsuario implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		pedido.setAttribute("setor", new SetorDAO().getAll());
		pedido.setAttribute("cargo", new CargoDAO().getAll());
		
		return "/cadastrousuario.jsp";
	}

}
