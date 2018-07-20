package logica;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProcessoDAO;
import entity.Processo;

public class TelaPrincipalTesoureiro implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		ProcessoDAO pdao = new ProcessoDAO();
		
		ArrayList<Processo> processosPagamento = pdao.getAllSemPagamento();
		
		pedido.getSession().setAttribute("processoSemPagamento", processosPagamento);
		return "/Tesoureiro/index.jsp";
	}

}
