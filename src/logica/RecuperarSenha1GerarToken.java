package logica;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import email.Email;
import entity.Usuario;

public class RecuperarSenha1GerarToken implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		String email = pedido.getParameter("email");

		Usuario u = new UsuarioDAO().getByEmail(email);
			
		if(u != null){
			//Gera o token
			int token = new Random().nextInt(8999) + 1000;
			//envia por email
			new Email().enviarCodigo(
				email, 
				token
			);
			return "esqueceuasenha2.html";
		}
		return "errosPag/erro.html";
	}


}
