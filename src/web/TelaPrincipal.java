package web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Cargo;

public class TelaPrincipal implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		String cargo = ((Cargo) pedido.getSession().getAttribute("cargoParaLogin")).getNome();
		
//		Por causa do nome da servlet de tela principal do gestor geral
		if (!cargo.equals("Gestor geral")) {
			return "sistema?logica=TelaPrincipal" + cargo +"&ordUser=nome&ordContrato=numero";
		} else {
			return "sistema?logica=TelaPrincipalGestorGeral";
		}
	}

}
