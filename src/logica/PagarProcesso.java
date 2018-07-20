package logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProcessoDAO;

public class PagarProcesso implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		String numeroSei = pedido.getParameter("n");
				
		new ProcessoDAO().atualizarPagamento(numeroSei);
		return "sistema?logica=TelaPrincipal";
	}

}
