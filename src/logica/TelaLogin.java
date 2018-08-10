/*
 * Limpa todos os dados da sessão e redireciona para tela de login 
 */

package logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Usuario;
import utilidades.AvisoVencimento;

public class TelaLogin implements Logica{
	private static boolean monitorandoVencimento = false;
	
	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		Usuario logado = (Usuario) pedido.getSession().getAttribute("usuario");
		
		if(logado != null){
			return "sistema?logica=TelaPrincipal";
		}
		
		if(!monitorandoVencimento){
//			para monitorar os vencimentos de contratos e avisar seus responsáveis
			AvisoVencimento monitorando = new AvisoVencimento();
//			monitorando.run();
			Thread monitorar = new Thread(monitorando);
			monitorar.start();
			
			monitorandoVencimento = true;
		}
		
		return "/login.jsp";
	}

}
