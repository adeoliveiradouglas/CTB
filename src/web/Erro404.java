/*Redireciona para p�gina de erro "n�o existe"*/

package web;

import javax.servlet.http.HttpServletRequest;

public class Erro404 implements Logica{

	@Override
	public String executa(HttpServletRequest pedido) throws Exception {
		return "/errosPag/404.html";
	}

}
