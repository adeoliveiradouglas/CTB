package main;

import java.io.File;

import utilidades.Planilha;

public class Teste {

	public static void main(String[] args) {
		File planilha = new File(new File(System.getProperty("user.home")), "Contrato PRODEB 2014_2018xlsx - Editável.xlsx");
				
		System.out.println(new Planilha().carregarXLSX(planilha, 1));
	}
}