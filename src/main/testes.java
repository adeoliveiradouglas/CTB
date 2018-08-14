package main;

import java.text.ParseException;

import utilidades.FormatarCampo;

public class testes {

	public static void main(String[] args) throws ParseException{
		FormatarCampo fc = new FormatarCampo();
		
		
		System.out.println(fc.stringToDecimal("145,00"));
	}

}
