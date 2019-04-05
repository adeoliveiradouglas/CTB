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
		 * Busca usuario no banco: Caso tenha usado usu�rio administrador geral,
		 * vai gerar uma exception de Null e esse usu�rio fica como nulo. �
		 * exatamente isso que deve acontecer pois o administrador geral n�o
		 * est� cadastrado no banco
		 */
		Usuario u = new UsuarioDAO().getByEmail(email);

		// criptografa senha digitada. A compara��o � feita com as senhas
		// criptografadas
		String senha = new Cripto().criptografa(pedido.getParameter("senha"));

		try {
			System.out.println(senha + " " + u.getNome());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
		}
		
		if (u != null && senha.equals(u.getSenha())) {
			// Caso o login seja v�lido
		} else if (email.equals("contratos.ctb@ctb.ba.gov.br")
				&& senha.equals("SZm6ez170MniprpMv9XhH5HVQ24JYbhs9Z9niOLSGH4=")) {
			// Desse modo, sempre existir� um usu�rio administrador, n�o
			// importando o que h� no banco de dados

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
				new Setor("09140271", "Subcoordenadoria de Tecnologia da Informa��o", "CTB/ TECI"),
				c
			);
		} else {
			// Senha errada, usu�rio inexistente...
			return "sistema?logica=Erro403";
		}
		// Coloca os dados do usu�rio na sess�o
		pedido.getSession().setAttribute("usuario", u);

		try{
			// P�e cargo em uso como o primeiro da lista do usu�rio
			pedido.getSession().setAttribute("cargoParaLogin", u.getCargo().get(0));
		}catch (IndexOutOfBoundsException e){
			return "sistema?logica=Erro403";
		}
		
		// Manda mostrar a tela principal
		return "sistema?logica=TelaPrincipal";
	}
}
