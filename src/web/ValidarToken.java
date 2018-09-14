/*
 * Segunda parte da recuperação de senha
 * Verifica se o número inserido pelo usuário é o mesmo token enviado para o email dele
 * Se sim, mostra tela para inserir nova senha
 * Se não, mostra a tela de acesso negado
 */

package web;

import javax.servlet.http.HttpServletRequest;

public class ValidarToken implements Logica{

	@Override
	public String executa(HttpServletRequest pedido) throws Exception {
		int gerado = (Integer) pedido.getSession().getAttribute("token"),
			inserido = Integer.parseInt(pedido.getParameter("codigo"));
		
		if (gerado == inserido){
			return "esqueceuasenha3.jsp";
		} else {
			return "sistema?logica=Erro403";
		}
	}

}
