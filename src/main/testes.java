package main;

import java.text.ParseException;

import dao.ContratoDAO;
import entity.Contrato;
import utilidades.Email;

public class testes {

	public static void main(String[] args) throws ParseException{
		Email e = new Email();
		
		Contrato c = new ContratoDAO().getByIdSemPagamento(13);
		
		e.aviso90dias("anderson.araujo@ctb.ba.gov.br", c);
	}

}
