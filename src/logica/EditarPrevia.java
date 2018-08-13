package logica;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Processo;

public class EditarPrevia implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		@SuppressWarnings("unchecked")
		ArrayList<Processo> previaProcessos = (ArrayList<Processo>) pedido.getSession().getAttribute("previaProcessos");
		
		int i = Integer.parseInt(pedido.getParameter("i"));
				
		previaProcessos.remove(i);
		
		pedido.getSession().setAttribute("previaProcessos", previaProcessos);		
		return "/Gestor/previaContrato.jsp";
	}

}
