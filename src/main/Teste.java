package main;

import dao.ContratoDAO;
import utilidades.Email;

public class Teste {

	public static void main(String[] args) {
		
		new Email().aviso45dias("contratos.ctb@ctb.ba.gov.br", new ContratoDAO().getAll().get(1));
	}
}