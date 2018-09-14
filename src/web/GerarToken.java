/*
 * Primeira parte da recuperação de senha
 * Gera um token numérico, envia para o email do solicitante e mostra uma página pedindo para inserir esse token  
 */

package web;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import dao.UsuarioDAO;
import entity.Usuario;
import utilidades.Email;

public class GerarToken implements Logica{

	@Override
	public String executa(HttpServletRequest pedido) throws Exception {
		String email = pedido.getParameter("email");

		Usuario u = new UsuarioDAO().getByEmail(email);
			
		if(u != null){
			//Gera o token
			int token = new Random().nextInt(8999) + 1000;

			pedido.getSession().setAttribute("token", token);
			pedido.getSession().setAttribute("email", email);
			//envia por email
			new Email().enviarCodigo(
				email, 
				token
			);
			return "esqueceuasenha2.jsp";
		}
		return "sistema?logica=Erro403";
	}
}
