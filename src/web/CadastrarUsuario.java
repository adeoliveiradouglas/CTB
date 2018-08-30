package web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CargoDAO;
import dao.SetorDAO;
import dao.UsuarioDAO;
import entity.Usuario;
import utilidades.Cripto;
import utilidades.Email;

public class CadastrarUsuario implements Logica {

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		String nome = pedido.getParameter("nome");
		String email = pedido.getParameter("email");
		UsuarioDAO undao = new UsuarioDAO("usuariosnovos");
		
		if (undao.getByEmail(email) == null && new UsuarioDAO().getByEmail(email) == null) {
			//se usu�rio com esse email N�O existe no sistema (usu�rio autorizado ou usu�rio a ser autorizado), ent�o pode ser adicionado			
			try {
				// insere no banco na tabela de novos usuarios
				undao.inserir(
					new Usuario(
						Integer.parseInt(pedido.getParameter("matricula")), 
						nome,
						email, 
						new Cripto().criptografa(pedido.getParameter("senha")), 
						new SetorDAO().getByCodigo(pedido.getParameter("setor")), 
						new CargoDAO().getById(Integer.parseInt(pedido.getParameter("cargo")))
					)
				);
			} catch (NumberFormatException e) {
				System.out.println(e);
			}

			// envia email informando cadastro
			new Email().enviarConfirmacaoCadastro(email, nome);

			// mostra p�gina informando confirma��o do cadastro
			return "/confirmacaocadastro.jsp";
		
		}//Fim do if que testa se o usu�rio j� existe no sistema
		
		else return "/errosPag/usuarioexiste.html";
	}
	
	

}
