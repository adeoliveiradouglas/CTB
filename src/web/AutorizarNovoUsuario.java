/*
 * Autoriza o acesso de um novo usuário
 * insere o registro dele na tabela "usuario" e o remove da tabela "usuariosnovos"  
 */
package web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import entity.Usuario;

public class AutorizarNovoUsuario implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		/*
		 * Recebe a posição que o usuario está na lista de usuários novos
		 */
		
		@SuppressWarnings("unchecked")
		List<Usuario> us = (List<Usuario>) pedido.getSession().getAttribute("usuariosnovos");
		
		Usuario u = us.get(Integer.parseInt(pedido.getParameter("id")));
		
//		Insere na tabela de usuários autorizados
		new UsuarioDAO().inserir(u);
		
		UsuarioDAO novos = new UsuarioDAO(true); 
//		remove da tabela de novos usuários
		novos.deleteById(u.getId());
		
		pedido.getSession().setAttribute("usuariosnovos", novos.getAll());
		
		return "sistema?logica=TelaPrincipal";
	}

}
