package logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;

public class RecusarNovoUsuario implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
//		remove da tabela de novos usuários
		new UsuarioDAO("usuariosnovos").deleteById(
			Integer.parseInt(pedido.getParameter("id"))
		);
		
		return "sistema?logica=TelaPrincipal";
	}

}
