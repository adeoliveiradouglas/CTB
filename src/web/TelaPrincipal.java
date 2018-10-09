package web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Cargo;

public class TelaPrincipal implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		int cargo = ((Cargo) pedido.getSession().getAttribute("cargoParaLogin")).getId();
		
		switch(cargo) {
			case 1:
				return "sistema?logica=TelaPrincipalAdministrador";
			
			case 2:
				return "sistema?logica=TelaPrincipalGestorGeral";
		
			case 3:
				return "sistema?logica=TelaPrincipalGestor";
		
			case 4:
				return "sistema?logica=TelaPrincipalTesoureiro";
				
			default: //caso seja diretor ou presidente
				return "sistema?logica=TelaPrincipalOnlyView";
		}
	}
}
