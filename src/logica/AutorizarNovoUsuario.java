/*
 * Autoriza o acesso de um novo usuário
 * insere o registro dele na tabela "usuario" e o remove da tabela "usuariosnovos"  
 */
package logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import dao.UsuarioNovoDAO;
import entity.Usuario;

public class AutorizarNovoUsuario implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		String email = pedido.getParameter("email");
		UsuarioNovoDAO undao = new UsuarioNovoDAO();
		
//		GAMBIARRA PARA TER OBJETO USUARIO ATRAVÉS DO EMAIL
//		SE RECEBER USUARIO DA JSP, VEM COMO STRING. PARA AGILIZAR O DESENVOLVIMENTO, USEI ESSA GAMBIARRA
//		MODO CORRETO: FAZER A CONVERSÃO DE STRING PARA UM OBJETO USUÁRIO
		Usuario u = undao.getByEmail(email);
		
//		Insere na tabela de usuários autorizados
		new UsuarioDAO().inserir(u);
		
//		remove da tabela de novos usuários
		undao.deleteByMatricula(u.getMatricula());
		
		return "sistema?logica=TelaPrincipal";
	}

}
