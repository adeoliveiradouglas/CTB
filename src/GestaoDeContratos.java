import java.util.Date;

import utilidades.Data;

/*
 * Classe main não utilizada no decorrer do programa
 * Usada somente para testes durante o desenvolvimento
 */

public class GestaoDeContratos {

	public static void  main(String[] args){
		Date d = new Date();
		Data teste = new Data(d);
		
		System.out.println(teste.getData().toString("dd/MM/yyyy"));
		
	}

}