package filtro;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Usuario;

	/*
	 * Respons�vel por bloquear acesso �s p�ginas da pasta Comum
	 * Comum � uma pasta onde ficam as p�ginas que s�o vistas por mais de um tipo de usu�rio
	 */

	@WebFilter(
		urlPatterns = {"/Comum/*"},
		dispatcherTypes = {
			DispatcherType.REQUEST, 
			DispatcherType.FORWARD
		}
	)
	public class Comum implements Filter{
		@Override
		public void doFilter(ServletRequest pedido, ServletResponse resposta, FilterChain chain)
				throws IOException, ServletException {
			
			HttpServletRequest req = (HttpServletRequest) pedido;
			HttpServletResponse res = (HttpServletResponse) resposta;
			HttpSession sessao = req.getSession(false);
			Usuario u = (Usuario) sessao.getAttribute("usuario");
			
			try {
				if (u.getCargo().getId() != 4)
					//Com exce��o do tesoureiro, todos os outros cargos podem ver as p�ginas dessa pasta
					chain.doFilter(pedido, resposta);
				else
					res.sendRedirect("/gestaodecontratos/sistema?logica=Erro403");
			}catch(Exception e){
				res.sendRedirect("/gestaodecontratos/sistema?logica=ErroDeslogado");
			}
		}
		
		@Override
		public void destroy() {}
		
		@Override
		public void init(FilterConfig arg0) throws ServletException {}

	}
