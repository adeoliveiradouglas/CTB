package logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProcessoDAO;
import entity.Usuario;

public class PagarProcesso implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		String numeroSei = pedido.getParameter("n");
		int idTesoureiro = ((Usuario) pedido.getSession().getAttribute("usuario")).getId();
		
		new ProcessoDAO().atualizarPagamento(numeroSei, idTesoureiro);
		
		return "sistema?logica=TelaPrincipal";
	}

}
