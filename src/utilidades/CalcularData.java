/* 
 * Classe usada para calcular a dist�ncia entre data
 * usada para saber quantos dias faltam para o vencimento do contrato 
 * funciona tanto com uma data espec�fica no momento da chamada do m�todo quanto passando uma data na instacia��o do objeto
 */

package utilidades;

import org.joda.time.DateTime;
import org.joda.time.Days;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CalcularData {
	private DateTime hoje = new DateTime(),
			 outra = hoje;
	private Days d;
	
	public CalcularData(DateTime proxima) {
		this.outra = proxima;
	}
	
	//retorna a quantidade de dias entre a data inserida e a data de hoje
	public int diasEntre(DateTime data) {
		d = Days.daysBetween(hoje, data);
		
		//+ 1 pois o getDays vai at� um dia antes ao dia escolhido 
		return d.getDays() + 1;
	}
	
	public int diasEntre() {
		d = Days.daysBetween(hoje, outra);
		return d.getDays() + 1;
	}
	
	//retorna true se a dist�ncia entre hoje e a data inserida for menor que 90 dias
	public boolean faltam90dias(DateTime data) {
		if (diasEntre(data) <= 90)
			return true;
		
		return false;
	}
	
	public boolean faltam90dias() {
		if (diasEntre(outra) <= 90)
			return true;
		
		return false;
	}
	
	//retorna true se a dist�ncia entre hoje e a data inserida for menor que 60 dias
	public boolean faltam60dias(DateTime data) {
		if (diasEntre(data) <= 60)
			return true;
		
		return false;
	}
	
	public boolean faltam60dias() {
		if (diasEntre(outra) <= 60)
			return true;
		
		return false;
	}
	
	//retorna true se a dist�ncia entre hoje e a data inserida for menor que 45 dias
	public boolean faltam45dias(DateTime data) {
		if (diasEntre(data) <= 45)
			return true;
		
		return false;
	}
	
	public boolean faltam45dias() {
		if (diasEntre(outra) <= 45)
			return true;
		
		return false;
	}		
}
