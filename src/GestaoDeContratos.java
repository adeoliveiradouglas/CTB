import java.math.BigDecimal;
import java.text.ParseException;

import utilidades.FormatarCampo;

/*
 * Classe main não utilizada no decorrer do programa
 * Usada somente para testes durante o desenvolvimento
 */

public class GestaoDeContratos {

	public static void  main(String[] args) throws ParseException  {
		FormatarCampo f = new FormatarCampo();
		BigDecimal b = new BigDecimal("111111111.00");
		
		
		System.out.println(f.decimalToString(b));
	}

}