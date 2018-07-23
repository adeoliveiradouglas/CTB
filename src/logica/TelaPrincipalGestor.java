package logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContratoDAO;
import entity.Usuario;

public class TelaPrincipalGestor implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		int idGestor = ((Usuario) pedido.getSession().getAttribute("usuario")).getId();
		String ordenacao = pedido.getParameter("ordContrato");
		
		pedido.getSession().setAttribute("contratos", new ContratoDAO().getByGestor(idGestor, ordenacao));
		pedido.getSession().setAttribute("contratosFiscal", new ContratoDAO().getByFiscal(idGestor, ordenacao));
		
		return "/Gestor/index.jsp";
	}

}
