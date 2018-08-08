/*Respons�vel por restringir o acesso �s p�ginas de gestor*/

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

@WebFilter(
	urlPatterns = {"/Presidente/*"}, 
	dispatcherTypes = {
		DispatcherType.REQUEST, 
		DispatcherType.FORWARD
	}
)
public class LoginPresidente implements Filter{

	@Override
	public void doFilter(ServletRequest pedido, ServletResponse resposta, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) pedido;
		HttpServletResponse res = (HttpServletResponse) resposta;
		HttpSession sessao = req.getSession(false);

/*		A l�gica respons�vel por autenticar o usu�rio insere os dados dele na sess�o
 * 		Aqui recupera os dados da sess�o e verifica se o usu�rio tem autoriza��o para acessar essa p�gina atrav�s do cargo dele
 * */
		try {
			if (((Usuario) sessao.getAttribute("usuario")).getCargo().getId() == 6)
				chain.doFilter(pedido, resposta);
			else
				res.sendRedirect("/gestaodecontratos/sistema?logica=Erro403");
		}catch(Exception e){
			res.sendRedirect("/gestaodecontratos/sistema?logica=ErroDeslogado");
		}			
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}
	@Override
	public void destroy() {}
}