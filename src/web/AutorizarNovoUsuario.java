/*
 * Autoriza o acesso de um novo usu�rio
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
		 * Recebe a posi��o que o usuario est� na lista de usu�rios novos
		 */
		
		@SuppressWarnings("unchecked")
		List<Usuario> us = (List<Usuario>) pedido.getSession().getAttribute("usuariosnovos");
		
		Usuario u = us.get(Integer.parseInt(pedido.getParameter("id")));
		
//		Insere na tabela de usu�rios autorizados
		new UsuarioDAO().inserir(u);
		
		UsuarioDAO novos = new UsuarioDAO(true); 
//		remove da tabela de novos usu�rios
		novos.deleteById(u.getId());
		
		pedido.getSession().setAttribute("usuariosnovos", novos.getAll());
		
		return "sistema?logica=TelaPrincipal";
	}

}
