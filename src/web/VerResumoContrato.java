package web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Contrato;
import entity.Dados;

public class VerResumoContrato implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		Contrato contrato = (Contrato) pedido.getSession().getAttribute("contratoVisualizar");
		List<Dados> processos = contrato.getProcessos();
		
		
		
		return "/Comum/resumoDoContrato.jsp";
	}

}
