/*Responsável por restringir o acesso às páginas de gestor geral*/

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

/*@WebFilter(
	urlPatterns = {"/Gestor geral/*"}, 
	dispatcherTypes = {
		DispatcherType.REQUEST, 
		DispatcherType.FORWARD
	}
)*/
public class LoginGestorGeral implements Filter{

	@Override
	public void doFilter(ServletRequest pedido, ServletResponse resposta, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) pedido;
		HttpServletResponse res = (HttpServletResponse) resposta;
		HttpSession sessao = req.getSession(false);

/*		A lógica responsável por autenticar o usuário insere os dados dele na sessão
 * 		Aqui recupera os dados da sessão e verifica se o usuário tem autorização para acessar essa página através do cargo dele
 * */
		if (((Usuario) sessao.getAttribute("usuario")).getCargo().getId() == 2)
			chain.doFilter(pedido, resposta);
		else
			res.sendRedirect("sistema?logica=Erro403");			
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}
	@Override
	public void destroy() {}
}
