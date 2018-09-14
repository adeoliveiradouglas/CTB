/*Redireciona para página de erro "falta de acesso"*/
package web;

import javax.servlet.http.HttpServletRequest;

public class ErroArquivoInexistente implements Logica{

	@Override
	public String executa(HttpServletRequest pedido) throws Exception {
		return "/errosPag/arquivoNaoEncontrado.html";
	}

}
