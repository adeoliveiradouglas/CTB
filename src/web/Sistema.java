/*
 * Servlet padr�o para redirecionamento de p�ginas
 * uso = sistema?logica=oquefazer
 */

package web;

import javax.annotation.ManagedBean;

import utilidades.AvisoVencimento;

@ManagedBean
public class Sistema {
	
	protected void iniciar(){
//		para monitorar os vencimentos de contratos e avisar seus respons�veis
		AvisoVencimento monitorando = new AvisoVencimento();
		Thread monitorar = new Thread(monitorando);
//		monitorar.start();


	}
}
