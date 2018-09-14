package web;

import javax.annotation.ManagedBean;
import javax.servlet.http.HttpServletRequest;

import dao.ContratoDAO;
import entity.Usuario;

@ManagedBean
public class TelaPrincipalGestor{

	public String executa() throws Exception {
		HttpServletRequest pedido = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		int idGestor = ((Usuario) pedido.getSession().getAttribute("usuario")).getId();
		String ordenacao = pedido.getParameter("ordContrato");
		
		pedido.getSession().setAttribute("contratos", new ContratoDAO().getByGestor(idGestor, ordenacao));
		pedido.getSession().setAttribute("contratosFiscal", new ContratoDAO().getByFiscal(idGestor, ordenacao));
		
		return "/Gestor/index.jsp";
	}

}
