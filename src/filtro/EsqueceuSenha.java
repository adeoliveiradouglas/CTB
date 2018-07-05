/*
 * Responsável por bloquear acesso às páginas subsequentes do processo de esqueceu a senha com link direto 
 */

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

@WebFilter(
	urlPatterns = {"/esqueceuasenha2.jsp", "/esqueceuasenha3.jsp"},
	dispatcherTypes = {
		DispatcherType.REQUEST, 
		DispatcherType.FORWARD
	}
)
public class EsqueceuSenha implements Filter{
	@Override
	public void doFilter(ServletRequest pedido, ServletResponse resposta, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) pedido;
		int token = (int) req.getSession().getAttribute("token");

		try{
			if (token >= 1000 && token <= 9999){
				chain.doFilter(pedido, resposta);
			}
		} catch (NullPointerException e){
			((HttpServletResponse) resposta).sendRedirect("esqueceuasenha.jsp");
		}
	}
	
	@Override
	public void destroy() {}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {}

}
