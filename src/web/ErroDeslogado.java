/*Redireciona para página de erro "não existe"*/

package web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErroDeslogado implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		return "/errosPag/deslogado.html";
	}

}
