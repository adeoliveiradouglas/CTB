import java.util.ArrayList;

import dao.ProcessoDAO;
import entity.Processo;
import utilidades.FormatarCampo;

/*
 * Classe main não utilizada no decorrer do programa
 * Usada somente para testes durante o desenvolvimento
 */

public class GestaoDeContratos {

	public static void  main(String[] args){
		ArrayList<Processo> p = new ProcessoDAO().getByContrato(12); 
		FormatarCampo fc = new FormatarCampo();
		
		System.out.println(fc.dataToString(p.get(1).getDataProcesso()));
		
	}

}