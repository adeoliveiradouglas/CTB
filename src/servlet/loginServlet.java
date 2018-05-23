/*
 * Servlet responsável por autenticar o usuário no sistema 
 */

package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SuppressWarnings("serial")
@WebServlet("/loginservlet") 
public class loginServlet extends HttpServlet{
	@Override
	protected void service (HttpServletRequest pedido, HttpServletResponse resposta) throws IOException{
	   PrintWriter out = resposta.getWriter();
	   /*PasswordEncoder pe = new BCryptPasswordEncoder();
	   String senha = pe.encode(pedido.getParameter("senha"));*/
	   String senha = pedido.getParameter("senha");
	   out.println("<html>");
       out.println("<body>");
       out.println(senha);
       out.println("</body>");
       out.println("</html>");
    }
}
