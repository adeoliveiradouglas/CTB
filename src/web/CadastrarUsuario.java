package web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import entity.Cargo;
import entity.Setor;
import entity.Usuario;
import utilidades.Cripto;
import utilidades.Email;

public class CadastrarUsuario implements Logica {

	@Override
	@SuppressWarnings("unchecked")
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		String nome = pedido.getParameter("nome");
		String email = pedido.getParameter("email");
		UsuarioDAO undao = new UsuarioDAO(true); //DAO para acesso à tabela de novos usuários
		
		if (undao.getByEmail(email) == null && new UsuarioDAO().getByEmail(email) == null) {
			//se usuário com esse email NÃO existe no sistema (usuário autorizado ou usuário a ser autorizado), então pode ser adicionado			
			ArrayList<Cargo> todosOsCargos = ((ArrayList<Cargo>) pedido.getSession().getAttribute("cargo"));
			ArrayList<Setor> setores = ((ArrayList<Setor>) pedido.getSession().getAttribute("setor"));
			
			//tenta gerar lista de cargos do usuário
			ArrayList<Cargo> cargosDoUsuario = new ArrayList<>();
			
			//cargo1 sempre vai funcionar pois a pessoa deve escolher um cargo
			int cargo1 = Integer.parseInt(pedido.getParameter("cargo1")),
				cargo2;
			
			//cargo2 não vai funcionar se o usuário só inseriu o primeiro cargo
			try{
				cargo2 = Integer.parseInt(pedido.getParameter("cargo2"));
			}catch(Exception e){
				//coloca cargo2 igual a cargo1 e posteriormente, no DAO, verifica se são iguais para não gerar dados duplicados no banco
				cargo2 = cargo1;
			}
							
			cargosDoUsuario.add(todosOsCargos.get(cargo1));			
			cargosDoUsuario.add(todosOsCargos.get(cargo2));
			
			Setor setor = setores.get(Integer.parseInt(pedido.getParameter("setor")));
			
			try {
				// insere no banco na tabela de novos usuarios
				undao.inserir(
					new Usuario(
						0,
						Integer.parseInt(pedido.getParameter("matricula")), 
						nome,
						email, 
						new Cripto().criptografa(pedido.getParameter("senha")), 
						setor,
						cargosDoUsuario
					)
				);
				
				// envia email informando cadastro
				new Email().enviarConfirmacaoCadastro(email, nome);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}

			// mostra página informando confirmação do cadastro
			return "/confirmacaocadastro.jsp";
		
		}//Fim do if que testa se o usuário já existe no sistema
		
		else return "/errosPag/usuarioexiste.html";
	}
	
	

}
