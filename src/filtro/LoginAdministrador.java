package filtro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import entity.Usuario;

/*@WebFilter(urlPatterns = {"/Administrador/*", "/sistema?logica=Login"})*/
@WebFilter("/sistema?logica=Login")
public class LoginAdministrador implements Filter{

	@Override
	public void doFilter(ServletRequest pedido, ServletResponse resposta, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) pedido;
		HttpSession sessao = req.getSession();
		
		
		if (
			((Usuario) sessao.getAttribute("usuario")).getCargo() 
			.equals(
			"Administrador")
		   )
			chain.doFilter(pedido, resposta);
		else
			req.getRequestDispatcher("/errosPag/403.html").forward(pedido, resposta);
			
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}
	@Override
	public void destroy() {}
}
