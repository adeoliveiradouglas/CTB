/*Redireciona para página de erro "não existe"*/

package web;

import javax.servlet.http.HttpServletRequest;

public class ErroDeslogado implements Logica{

	@Override
	public String executa(HttpServletRequest pedido) throws Exception {
		return "/errosPag/deslogado.html";
	}

}
