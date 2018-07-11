/*
 * Carrega dados para página de cadastro de contrato
 * Lista de Gestores
 * Lista de Fiscais é a mesma de gestores
 * Lista de Usos
 * Lista de Fontes pagantes
 * Lista de Recursos 
 */

package logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OutroDAO;
import dao.UsuarioDAO;

public class TelaNovoContrato implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		final String tabelaRecurso = "recurso",
					 tabelaUso = "uso",
					 tabelaFonte = "fontepagante";
		
		pedido.getSession().setAttribute(tabelaRecurso, new OutroDAO(tabelaRecurso).getAll());
		pedido.getSession().setAttribute(tabelaUso, new OutroDAO(tabelaUso).getAll());
		pedido.getSession().setAttribute(tabelaFonte, new OutroDAO(tabelaFonte).getAll());
		pedido.getSession().setAttribute("gestor", new UsuarioDAO().getAllGestor());
		return "/Gestor geral/novoContrato.jsp";
	}

}
