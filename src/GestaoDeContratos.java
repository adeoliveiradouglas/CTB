import java.math.BigDecimal;
import java.util.ArrayList;

import dao.ContratoDAO;
import entity.Contrato;
import entity.Processo;

/*
 * Classe main não utilizada no decorrer do programa
 * Usada somente para testes durante o desenvolvimento
 */

public class GestaoDeContratos {

	public static void main(String[] args)  {
		ArrayList<Contrato> cs;
		ContratoDAO cdao = new ContratoDAO();
		
		cs = cdao.getByGestor(3);
		
		BigDecimal a = new BigDecimal("0"),
				   b = new BigDecimal("1"),
				   d = a.add(b);
		
		
		
		System.out.println(d);
		for (Contrato c: cs){
			System.out.println(c.getValorInicial());
			System.out.println(c.getValorAditivos());
			System.out.println(c.getValorTotal());
			for (Processo p: c.getProcessos())
				System.out.println(p.getAditivo());
		}
		
	}
	
}
