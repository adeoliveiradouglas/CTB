package logica;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import dao.UsuarioNovoDAO;
import email.Email;
import entity.Usuario;
import sun.misc.BASE64Encoder;

public class CadastrarUsuario implements Logica {

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		String nome = pedido.getParameter("nome");
		String email = pedido.getParameter("email");
		UsuarioNovoDAO undao = new UsuarioNovoDAO();
		
		if (undao.getByEmail(email) == null && new UsuarioDAO().getByEmail(email) == null) {
			//se usuário com esse email NÃO existe no sistema (usuário autorizado ou usuário a ser autorizado), então pode ser adicionado			
			try {
				// insere no banco na tabela de novos usuarios
				undao.inserir(
					new Usuario(
						Integer.parseInt(pedido.getParameter("matricula")), 
						nome,
						email, 
						criptografa(pedido.getParameter("senha")), 
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
			return "/confirmacaocadastro.jsp";
		
		}//Fim do if que testa se o usuário já existe no sistema
		
		else return "/errosPag/usuarioexiste.html";
	}
	
	private String criptografa(String senha){
		/*
		 * Código retirado do site http://www.guj.com.br/t/cadastro-de-usuario-com-senha-criptografada/192679
		 */
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(senha.getBytes());
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(digest.digest());
		} catch (NoSuchAlgorithmException ns) {
			ns.printStackTrace();
		}
		return senha;
	}

}
