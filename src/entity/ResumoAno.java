/*
 * Classe usada para representar o ano no resumo do contrato 
 * É usada somente quando o usuário pede o resumo do contrato na tela de visualização da planilha
 */
package entity;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import utilidades.FormatarCampo;

@ToString
@Getter
public class ResumoAno {
	int ano;
	BigDecimal total = new BigDecimal("0"),
			   aditivo = new BigDecimal("0");
	@Setter
	BigDecimal saldo = new BigDecimal("0");
	
	public ResumoAno(int ano) {
		this.ano = ano;
	}
	
	public void somaTotal(BigDecimal valor) {
		total = total.add(valor);
	}
	
	public void somaAditivo(BigDecimal valor) {
		aditivo = aditivo.add(valor); 
	}
	
	public String getTotalAsString() {
		return new FormatarCampo().decimalToString(total);
	}
	
	public String getAditivoAsString() {
		return new FormatarCampo().decimalToString(aditivo);
	}
	
	public String getSaldoAsString() {
		return new FormatarCampo().decimalToString(saldo);
	}
}
