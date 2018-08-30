/*
 * Autoriza o acesso de um novo usu�rio
 * insere o registro dele na tabela "usuario" e o remove da tabela "usuariosnovos"  
 */
package web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import entity.Usuario;

public class AutorizarNovoUsuario implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		/*
		 * Recebe a posi��o que o usuario est� na lista de usu�rios novos
		 */
		
		@SuppressWarnings("unchecked")
		ArrayList<Usuario> us = (ArrayList<Usuario>) pedido.getSession().getAttribute("usuariosnovos");
		
		Usuario u = us.get(Integer.parseInt(pedido.getParameter("id")));
		
//		Insere na tabela de usu�rios autorizados
		new UsuarioDAO().inserir(u);
		
//		remove da tabela de novos usu�rios
		new UsuarioDAO("usuariosnovos").deleteById(u.getId());
		
		return "sistema?logica=TelaPrincipal";
	}

}
