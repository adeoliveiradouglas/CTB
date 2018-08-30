package web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;

public class RemoveUsuario implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		String tabela = pedido.getParameter("tabela");
//		remove da tabela de usuários
		new UsuarioDAO(tabela).deleteById(
			Integer.parseInt(pedido.getParameter("id"))
		);
		
		return "sistema?logica=TelaPrincipal";
	}

}
