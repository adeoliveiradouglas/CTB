/*Responsável por restringir o acesso às páginas de tesoureiro*/

package filtro;

import java.io.IOException;
import java.util.List;

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

import entity.Cargo;
import entity.Usuario;

@WebFilter(
	urlPatterns = {"/Tesoureiro/*"}, 
	dispatcherTypes = {
		DispatcherType.REQUEST, 
		DispatcherType.FORWARD
	}
)
public class LoginTesoureiro implements Filter{

	@Override
	public void doFilter(ServletRequest pedido, ServletResponse resposta, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) pedido;
		HttpServletResponse res = (HttpServletResponse) resposta;
		HttpSession sessao = req.getSession(false);

/*		A lógica responsável por autenticar o usuário insere os dados dele na sessão
 * 		Aqui recupera os dados da sessão e verifica se o usuário tem autorização para acessar essa página através do cargo dele
 */
		List<Cargo> cargosDoUsuario = ((Usuario) sessao.getAttribute("usuario")).getCargo();
		try {
			if (cargosDoUsuario.get(0).getId() == 4 || cargosDoUsuario.get(1).getId() == 4)
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
