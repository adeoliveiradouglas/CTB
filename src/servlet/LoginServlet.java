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

import dao.UsuarioDAO;
import entity.Usuario;

@WebServlet("/loginservlet") 
@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet{
	@Override
	protected void service (HttpServletRequest pedido, HttpServletResponse resposta) throws IOException{
	   PrintWriter out = resposta.getWriter();
	   PasswordEncoder pe = new BCryptPasswordEncoder();
	   UsuarioDAO dbUsuario = new UsuarioDAO("gestaodecontratos","douglas", "administrador", "10.95.1.247");
	   Usuario u;
	   String email = pedido.getParameter("email");
//	   Busca usuario no banco usando o email
	   try {
		   u = dbUsuario.getByEmail(email);
	   } catch (Exception e){
		   u = null;
	   }
//	   Criptografa senha inserida
	   String senha = pe.encode(pedido.getParameter("senha"));
	   
	   out.println("<html>");
       out.println("<body>");
       
//     Compara senhas criptografadas
	   if (u != null){
		   if(pedido.getParameter(senha).equals(u.getSenha())){
//			   Caso a senha esteja correta
			   out.println("<p>logado</p>");
		   } else {
//			   Caso senha errada
			   out.println("senha incorreta ou usuario não existe");
		   } 
	   } else {
//		   Caso usuario não exista
		   out.println("senha incorreta ou usuario não existe");
	   }
       out.println("</body>");
       out.println("</html>");
    }
}
