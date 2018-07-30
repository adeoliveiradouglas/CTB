package logica;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProcessoDAO;
import entity.Contrato;
import entity.Processo;

public class TelaPrincipalTesoureiro implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		ProcessoDAO pdao = new ProcessoDAO();
		
		ArrayList<Processo> processosPagamento = pdao.getAllSemPagamento();
		
		
		ArrayList<Contrato> contratosSemPagamento = null;
		pedido.getSession().setAttribute("contratosSemPagamento", contratosSemPagamento);
		return "/Tesoureiro/index.jsp";
	}

}
