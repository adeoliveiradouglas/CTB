package logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import entity.Usuario;
import utilidades.Cripto;

public class Login implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		String email = pedido.getParameter("email");
		
		//Busca usuario no banco
		Usuario u = new UsuarioDAO().getByEmail(
						email
					);
		
		//criptografa senha digitada. A comparação é feita com as senhas criptografadas
		String senha = new Cripto().criptografa(
			pedido.getParameter("senha")
		);
		
		if(u != null && senha.equals(u.getSenha())){
	
		} else if (email.equals("contrato.ctb@ctb.ba.gov.br") && senha.equals("SZm6ez170MniprpMv9XhH5HVQ24JYbhs9Z9niOLSGH4=")){
//			Desse modo, sempre existirá um usuário administrador, não importando o que há no banco de dados
			
//			Cria um objeto usuario para Administrador
			u = new Usuario(0, "Administrador", email, senha, "CTB/ TECI", "Administrador");
		} else {
			return "sistema?logica=Erro403";
		}
		
		
//		Coloca os dados do usuário na sessão
		pedido.getSession().setAttribute("usuario", u);
		
//		Muda o tempo de inatividade para 5 minutos (o padrão são 30 minutos)
		pedido.getSession().setMaxInactiveInterval(300);
		
//		Retorna a página do respectivo cargo que deve ser acessada 
		return "sistema?logica=TelaPrincipal";
	}
}
