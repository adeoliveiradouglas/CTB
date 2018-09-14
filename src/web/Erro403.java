/*Redireciona para página de erro "falta de acesso"*/
package web;

import javax.servlet.http.HttpServletRequest;

public class Erro403 implements Logica{

	@Override
	public String executa(HttpServletRequest pedido) throws Exception {
		return "/errosPag/403.html";
	}

}
