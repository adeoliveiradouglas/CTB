package logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioNovoDAO;

public class RecusarNovoUsuario implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
//		remove da tabela de novos usuários
		new UsuarioNovoDAO().deleteByMatricula(
			Integer.parseInt(pedido.getParameter("matricula"))
		);
		
		return "sistema?logica=TelaPrincipal";
	}

}
