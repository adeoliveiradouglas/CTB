/*
 * Classe usada para formatar o valor que vem da tela para um tipo interno do banco
 * Para fazer: retornar o valor para o formato da tela
 * formato da tela: XXX.XXX.XXX,XX
 * formato do banco: XXXXXXXXX.XX
 *  
 */

package utilidades;

import java.math.BigDecimal;
import java.util.Date;

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
				aux = "0,00";
		
		try {
			parameter = parameter.replace(".", ",");
					
			int ivirgula = parameter.indexOf(","),
				iaux = 0;
			
			aux = parameter.substring(0, ivirgula);
			
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
		} catch (Exception e) {	}
	
		return aux;
	}

	public String cnpjToBd(String cnpj) {
//		verifica tamanho do campo do cnpj
		if(cnpj.length() > 18){
			cnpj = cnpj.substring(0, 18);
		}
		return cnpj;
	}
	
	public String dataToString(Date data){
		String antigo = "" + data,
			   novo = "";
		
		try {
			novo = antigo.substring(8, 10) + "/" + antigo.substring(5, 7) + "/" + antigo.substring(0, 4);
		} catch (Exception e) {
//			e.printStackTrace();
		}
		
		return novo;
	}
	
	public String mesToInt(String mes){
		switch(mes){
			case "Janeiro":
				return "01";
				
			case "Fevereiro":
				return "02";
				
			case "Março":
				return "03";
				
			case "Abril":
				return "04";
				
			case "Maio":
				return "05";
			
			case "Junho":
				return "06";
				
			case "Julho":
				return "07";
				
			case "Agosto":
				return "08";
				
			case "Setembro":
				return "09";
				
			case "Outubro":
				return "10";
				
			case "Novembro":
				return "11";
				
			case "Dezembro":
				return "12";
			
			default:
				return mes;
		}
	}
	
	public String intToMonth(int m){
		switch(m){
			case 1:
				return "Janeiro";
				
			case 2:
				return "Fevereiro";
				
			case 3:
				return "Março";
				
			case 4:
				return "Abril";
				
			case 5:
				return "Maio";
				
			case 6:
				return "Junho";
				
			case 7:
				return "Julho";
				
			case 8:
				return "Agosto";
				
			case 9:
				return "Setembro";
				
			case 10:
				return "Outubro";
				
			case 11:
				return "Novembro";
				
			case 12:
				return "Dezembro";
		}
		return "";
	}
}
