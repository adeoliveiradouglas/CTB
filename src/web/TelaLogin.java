/*
 * Redireciona para tela de login
 * inicia monitoramento de vencimento dos contratos 
 */

package web;

import javax.servlet.http.HttpServletRequest;

import entity.Usuario;

public class TelaLogin implements Logica{
	
	
	@Override
	public String executa(HttpServletRequest pedido) throws Exception {
		Usuario logado = (Usuario) pedido.getSession().getAttribute("usuario");
		
		if(logado != null){
			return "sistema?logica=TelaPrincipal";
		}
		
		return "/login.xhtml";
	}

}
