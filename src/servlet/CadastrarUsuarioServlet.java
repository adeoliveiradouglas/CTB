package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioNovoDAO;
import email.Email;
import entity.Usuario;

@WebServlet("/cadastrarusuarioservlet")
public class CadastrarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = -325209724266609709L;

	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta) throws IOException, ServletException {
		String nome = pedido.getParameter("nome");
		String email = pedido.getParameter("email");
		UsuarioNovoDAO undao = new UsuarioNovoDAO();
//		Usuario tes = undao.getByEmail(email);
		
		if (undao.getByEmail(email) == null/* && new UsuarioDAO().getByEmail(email) == null*/) {
			//se usuário com esse email NÃO existe no sistema (usuário autorizado ou usuário a ser autorizado), então pode ser adicionado			
			try {
				// insere no banco na tabela de novos usuarios
				undao.inserir(
					new Usuario(
						Integer.parseInt(pedido.getParameter("matricula")), 
						nome,
						email, 
						pedido.getParameter("senha"), 
						pedido.getParameter("setor"), 
						pedido.getParameter("cargo")
					)
				);
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}

			// envia email informando cadastro
			new Email().enviarConfirmacaoCadastro(email, nome);

			// mostra página informando confirmação do cadastro
			pedido.getRequestDispatcher("/confirmacaocadastro.jsp").forward(pedido, resposta);
		
		}//Fim do if que testa se o usuário já existe no sistema
		
		else 
			pedido.getRequestDispatcher("/usuarioexiste").forward(pedido, resposta);
	}//Fim do método "service"
} //Fim da classe
