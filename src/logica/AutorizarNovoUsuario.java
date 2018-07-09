/*
 * Autoriza o acesso de um novo usu�rio
 * insere o registro dele na tabela "usuario" e o remove da tabela "usuariosnovos"  
 */
package logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import entity.Usuario;

public class AutorizarNovoUsuario implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		int id = Integer.parseInt(pedido.getParameter("id"));
		UsuarioDAO undao = new UsuarioDAO("usuariosnovos");
		
//		GAMBIARRA PARA TER OBJETO USUARIO ATRAV�S DO EMAIL
//		SE RECEBER USUARIO DA JSP, VEM COMO STRING. PARA AGILIZAR O DESENVOLVIMENTO, USEI ESSA GAMBIARRA
//		MODO CORRETO: FAZER A CONVERS�O DE STRING PARA UM OBJETO USU�RIO
		Usuario u = undao.getById(id);
		
//		Insere na tabela de usu�rios autorizados
		new UsuarioDAO().inserir(u);
		
//		remove da tabela de novos usu�rios
		undao.deleteById(u.getId());
		
		return "sistema?logica=TelaPrincipal";
	}

}
