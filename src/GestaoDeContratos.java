import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;

import dao.TesteDAO;

/*
 * Classe main não utilizada no decorrer do programa
 * Usada somente para testes durante o desenvolvimento
 */

public class GestaoDeContratos {

	public static void main(String[] args) throws ParseException {
		String v = "15654982.66";
		BigDecimal valor = new BigDecimal(v);
		
		new TesteDAO("teste").inserir(valor);
		ArrayList<BigDecimal> va = new TesteDAO("teste").getAll();
		
		for (BigDecimal a: va){
			System.out.println(a);
		}
		
	}
	
}
