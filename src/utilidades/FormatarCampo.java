/*
 * Classe usada para formatar o valor que vem da tela para um tipo interno do banco
 * Para fazer: retornar o valor para o formato da tela
 * formato da tela: XXX.XXX.XXX,XX
 * formato do banco: XXXXXXXXX.XX
 *  
 */

package utilidades;

import java.math.BigDecimal;

public class FormatarCampo{

	public FormatarCampo(){}
	
	public String stringToDecimal(String parameter) {
//		Tirar pontos do valor e mudar vírgula para ponto
		parameter = parameter.replace(".", "");
		parameter = parameter.replace(",", ".");
		
		return parameter;
	}
	
	public String decimalToString(BigDecimal b) {
//		pontos do valor e mudar vírgula para ponto
		String parameter = "" + b,
				aux = null;
		parameter = parameter.replace(".", ",");
		
		int ivirgula = parameter.indexOf(","),
			iaux = 0;
		
		aux = parameter.substring(0, ivirgula);
		
				
//		if(aux.length() > 3 && aux.length() < 7 )
		
		for (int i = ivirgula; i > 0; --i){
			switch(iaux){
				case 3:			
					aux = aux.substring(0, i) + "." + aux.substring(i, aux.length());
					break;
				case 6:
					aux = aux.substring(0, i) + "." + aux.substring(i, aux.length());
					break;
			}
			
			++iaux;
		}
		aux = aux + parameter.substring(ivirgula);
		
		return aux;
	}

	public String cnpjToBd(String cnpj) {
//		verifica tamanho do campo e afins do cnpj
		if(cnpj.length() > 18){
			cnpj = cnpj.substring(0, 18);
		}
		return cnpj;
	}
}
