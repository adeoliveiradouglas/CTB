/*
 * Carrega dados para página de cadastro de contrato
 * Lista de Gestores
 * Lista de Fiscais é a mesma de gestores
 * Lista de Usos
 * Lista de Fontes pagantes
 * Lista de Recursos 
 */

package logica;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OutroDAO;
import dao.UsuarioDAO;
import entity.Outro;

public class TelaNovoContrato implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		final String tabelaRecurso = "recurso",
					 tabelaUso = "uso",
					 tabelaFonte = "fontepagante";
		
//		Tenta recuperar uma lista da sessão
		@SuppressWarnings("unchecked")
		ArrayList<Outro> teste = (ArrayList<Outro>) pedido.getSession().getAttribute(tabelaFonte),
						 gestores = (ArrayList<Outro>) pedido.getSession().getAttribute("gestores");
		
		if(teste == null || gestores == null){
//			Para evitar acessos desnecessários ao banco de dados toda vez que chamar a página
//			Se a lista não existe, significa que deve carregar todas as outras também
			pedido.getSession().setAttribute(tabelaRecurso, new OutroDAO(tabelaRecurso).getAll());
			pedido.getSession().setAttribute(tabelaUso, new OutroDAO(tabelaUso).getAll());
			pedido.getSession().setAttribute(tabelaFonte, new OutroDAO(tabelaFonte).getAll());
			pedido.getSession().setAttribute("gestores", new UsuarioDAO().getAllGestor());
		}		
		
		return "/Gestor geral/novoContrato.jsp";
	}

}
