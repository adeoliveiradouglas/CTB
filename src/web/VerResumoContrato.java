package web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Contrato;
import entity.Processo;

public class VerResumoContrato implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		Contrato contrato = (Contrato) pedido.getSession().getAttribute("contratoVisualizar");
		List<Processo> processos = contrato.getProcessos();
		
		
		
		return "/Comum/resumoDoContrato.jsp";
	}

}
