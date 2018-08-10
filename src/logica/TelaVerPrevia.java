/*
 * Depois que importar planilha para contrato, mostra essa tela para aprovação do gestor antes de gravar no banco
 */

package logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TelaVerPrevia implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		// TODO Auto-generated method stub
		return "/Gestor/verPrevia.jsp";
	}

}
