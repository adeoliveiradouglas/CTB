import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import utilidades.FormatarCampo;

/*
 * Classe main não utilizada no decorrer do programa
 * Usada somente para testes durante o desenvolvimento
 */

public class GestaoDeContratos {

	public static void  main(String[] args) throws ParseException  {
		FormatarCampo f = new FormatarCampo();
		BigDecimal b = new BigDecimal("1500050.00");
		
		f.decimalToString(b);
	}

}