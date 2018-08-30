/*
 * Segunda parte da recupera��o de senha
 * Verifica se o n�mero inserido pelo usu�rio � o mesmo token enviado para o email dele
 * Se sim, mostra tela para inserir nova senha
 * Se n�o, mostra a tela de acesso negado
 */

package web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValidarToken implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		int gerado = (int) pedido.getSession().getAttribute("token"),
			inserido = Integer.parseInt(pedido.getParameter("codigo"));
		
		if (gerado == inserido){
			return "esqueceuasenha3.jsp";
		} else {
			return "sistema?logica=Erro403";
		}
	}

}
