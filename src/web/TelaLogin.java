/*
 * Redireciona para tela de login
 * inicia monitoramento de vencimento dos contratos 
 */

package web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Usuario;
import utilidades.AvisoVencimento;

public class TelaLogin implements Logica{
	private static boolean monitorandoVencimento = false;
	
	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		Usuario logado = (Usuario) pedido.getSession().getAttribute("usuarioLogado");
		
		if(logado != null){
			return "sistema?logica=TelaPrincipal";
		}
		
		if(!monitorandoVencimento){//se n�o estiver monitorando os contratos
//			para monitorar os vencimentos de contratos e avisar seus respons�veis
			AvisoVencimento monitorando = new AvisoVencimento();
			Thread monitorar = new Thread(monitorando);
			monitorar.start();
			
			monitorandoVencimento = true;
		}
		
		return "/login.jsp";
	}

}
