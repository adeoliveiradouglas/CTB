package web;

import javax.servlet.http.HttpServletRequest;

import entity.Cargo;
import entity.Usuario;

public class TrocarCargoEmUso implements Logica{

	@Override
	public String executa(HttpServletRequest pedido) throws Exception {
		Usuario u = (Usuario) pedido.getSession().getAttribute("usuario");
		int i = Integer.parseInt((String) pedido.getParameter("i"));
		
		Cargo c = u.getCargo().get(i);
		
		pedido.getSession().setAttribute("cargoParaLogin", c);
		
		return "sistema?logica=TelaPrincipal";
	}

}
