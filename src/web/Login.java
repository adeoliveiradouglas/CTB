package web;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import entity.Cargo;
import entity.Setor;
import entity.Usuario;
import utilidades.Cripto;

public class Login implements Logica {

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		String email = pedido.getParameter("email");

		/*
		 * Busca usuario no banco: Caso tenha usado usuário administrador geral,
		 * vai gerar uma exception de Null e esse usuário fica como nulo. É
		 * exatamente isso que deve acontecer pois o administrador geral não
		 * está cadastrado no banco
		 */
		Usuario u = new UsuarioDAO().getByEmail(email);

		// criptografa senha digitada. A comparação é feita com as senhas
		// criptografadas
		String senha = new Cripto().criptografa(pedido.getParameter("senha"));

		try {
			System.out.println(senha + " " + u.getNome());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
		}
		
		if (u != null && senha.equals(u.getSenha())) {
			// Caso o login seja válido
		} else if (email.equals("contratos.ctb@ctb.ba.gov.br")
				&& senha.equals("SZm6ez170MniprpMv9XhH5HVQ24JYbhs9Z9niOLSGH4=")) {
			// Desse modo, sempre existirá um usuário administrador, não
			// importando o que há no banco de dados

			// Cria um objeto usuario para Administrador
			List<Cargo> c = new ArrayList<Cargo>();
			c.add(new Cargo(1, "Administrador", ""));
			c.add(new Cargo(1, "", ""));
			
			u = new Usuario(
				0, 
				0,
				"Administrador", 
				email, 
				senha, 
				new Setor("09140271", "Subcoordenadoria de Tecnologia da Informação", "CTB/ TECI"),
				c
			);
		} else {
			// Senha errada, usuário inexistente...
			return "sistema?logica=Erro403";
		}
		// Coloca os dados do usuário na sessão
		pedido.getSession().setAttribute("usuario", u);

		try{
			// Põe cargo em uso como o primeiro da lista do usuário
			pedido.getSession().setAttribute("cargoParaLogin", u.getCargo().get(0));
		}catch (IndexOutOfBoundsException e){
			return "sistema?logica=Erro403";
		}
		
		// Manda mostrar a tela principal
		return "sistema?logica=TelaPrincipal";
	}
}
