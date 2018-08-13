package main;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;

import entity.Processo;
import utilidades.FormatarCampo;
import utilidades.Planilha;

public class testes {

	public static void main(String[] args) throws ParseException{
		FormatarCampo fc = new FormatarCampo();
		
		
		System.out.println(fc.stringToDecimal("145,00"));
	}

}
