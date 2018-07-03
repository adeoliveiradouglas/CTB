/*
 * Autoriza o acesso de um novo usu�rio
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
		
//		GAMBIARRA PARA TER OBJETO USUARIO ATRAV�S DO EMAIL
//		SE RECEBER USUARIO DA JSP, VEM COMO STRING. PARA AGILIZAR O DESENVOLVIMENTO, USEI ESSA GAMBIARRA
//		MODO CORRETO: FAZER A CONVERS�O DE STRING PARA UM OBJETO USU�RIO
		Usuario u = undao.getByEmail(email);
		
//		Insere na tabela de usu�rios autorizados
		new UsuarioDAO().inserir(u);
		
//		remove da tabela de novos usu�rios
		undao.deleteByMatricula(u.getMatricula());
		
		return "sistema?logica=TelaPrincipal";
	}

}
