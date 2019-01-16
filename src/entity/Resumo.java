/*
 * Classe usada para representar o ano e meses no resumo do contrato 
 * É usada somente quando o usuário pede o resumo do contrato na tela de visualização da planilha
 */
package entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import utilidades.FormatarCampo;

@ToString
@Getter
@AllArgsConstructor
public class Resumo {
	String marcacao; //guarda ano ou mês desse registro
	BigDecimal total,
			   aditivo,
			   totalAcumulado,
			   aditivoAcumulado;
	@Setter
	BigDecimal saldo;
	List<Resumo> meses = new ArrayList<>();
	
	public Resumo(String marcacao) {
		this.marcacao = marcacao;
		this.total = new BigDecimal(0);
		this.aditivo = new BigDecimal(0);
		this.totalAcumulado = new BigDecimal(0);
		this.aditivoAcumulado = new BigDecimal(0);
	}
	
	public Resumo(String marcacao, BigDecimal totalAcumulado, BigDecimal aditivoAcumulado) {
//		esse construtor só é chamado quando a classe é usada como ano a partir da segunda posição
		this.marcacao = marcacao;
		this.total = new BigDecimal(0);
		this.aditivo = new BigDecimal(0);
		this.totalAcumulado = totalAcumulado;
		this.aditivoAcumulado = aditivoAcumulado;
	}
	
	public Resumo(String mes, BigDecimal total, BigDecimal aditivo, BigDecimal saldo) {
//		esse construtor só é chamado quando a classe é usada como mês
		this.marcacao = mes;
		this.total = total;
		this.aditivo = aditivo;
		this.saldo = saldo;
		this.totalAcumulado = null;
		this.aditivoAcumulado = null;
		this.meses = null;
	}
		
	public void somaTotal(BigDecimal valor) {
		total = total.add(valor);
		somaTotalAcumulado(valor);
	}
	
	public void somaTotalAcumulado(BigDecimal valor) {
		totalAcumulado = totalAcumulado.add(valor);
	}
	
	public void somaAditivo(BigDecimal valor) {
		aditivo = aditivo.add(valor); 
		somaAditivoAcumulado(valor);
	}
	
	public void somaAditivoAcumulado(BigDecimal valor) {
		aditivoAcumulado = aditivoAcumulado.add(valor); 		
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
	
	public String getTotalAcumuladoAsString() {
		return new FormatarCampo().decimalToString(totalAcumulado);
	}
	
	public String getAditivoAcumuladoAsString() {
		return new FormatarCampo().decimalToString(aditivoAcumulado);
	}
}
