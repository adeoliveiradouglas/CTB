/*
 * Terceira e última parte da recuperação de senha
 * Depois de confirmar o token por email, recebe a nova senha, criptografa e atualiza o registro do banco 
 */

package logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import utilidades.Cripto;

public class AlterarSenha implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		String senha = pedido.getParameter("senha"),
			   cript = new Cripto().criptografa(senha);
		
		new UsuarioDAO().atualizarSenha(
			cript,
			(String) pedido.getSession().getAttribute("email")
		);
		
		return "sistema?logica=LoginTela";
	}

}
