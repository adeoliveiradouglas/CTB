package logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContratoDAO;
import entity.Usuario;

public class TelaPrincipalGestor implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		int idGestor = ((Usuario) pedido.getSession().getAttribute("usuario")).getId();
		pedido.getSession().setAttribute("contratos", new ContratoDAO().getByGestor(idGestor));
		pedido.getSession().setAttribute("contratosFiscal", new ContratoDAO().getByFiscal(idGestor));
		
		return "/Gestor/index.jsp";
	}

}
