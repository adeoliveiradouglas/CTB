/*
 * Autoriza o acesso de um novo usuário
 * insere o registro dele na tabela "usuario" e o remove da tabela "usuariosnovos"  
 */
package web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dao.UsuarioDAO;
import entity.Usuario;

public class AutorizarNovoUsuario implements Logica{

	@Override
	public String executa(HttpServletRequest pedido) throws Exception {
		/*
		 * Recebe a posição que o usuario está na lista de usuários novos
		 */
		
		@SuppressWarnings("unchecked")
		ArrayList<Usuario> us = (ArrayList<Usuario>) pedido.getSession().getAttribute("usuariosnovos");
		
		Usuario u = us.get(Integer.parseInt(pedido.getParameter("id")));
		
//		Insere na tabela de usuários autorizados
		new UsuarioDAO().inserir(u);
		
//		remove da tabela de novos usuários
		new UsuarioDAO(true).deleteById(u.getId());
		
		return "sistema?logica=TelaPrincipal";
	}

}
