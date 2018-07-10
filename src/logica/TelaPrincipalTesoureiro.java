package logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Usuario;

public class TelaPrincipalTesoureiro implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		return "/" + ((Usuario) pedido.getSession().getAttribute("usuario")).getCargo() + "/index.jsp";
	}

}
