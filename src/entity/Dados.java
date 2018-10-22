/*
 * Classe respons�vel por armazenar dados de um processo
 * Usando framework Lombok para gerar os getters e setters da classe atrav�s da anota��o "@Data"
 * Cada processo est� dentro de um contrato.
 * obs1: numeroSei � o n�mero do processo.
 * obs2: se valor do aditivo for nulo ou zero, ent�o n�o houve aditivo.
 * obs3: se n�o houver data de pagamento, � porque ele ainda n�o foi feito.
 * obs4: posi��o de contagem do processo no contrato
 */


package entity;

import java.math.BigDecimal;
import java.util.Date;

import org.joda.time.DateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import utilidades.FormatarCampo;

@ToString
public class Dados {
	private static final String formatoData = "dd/MM/yyyy";

	@Getter @Setter
	private String notaFiscal,
			tipoAditivo = null, // vide cabecalho obs2
			numeroSei, // vide cabecalho obs1
			ano, mes;
	
	@Getter @Setter
	private BigDecimal aditivo = null,  // vide cabecalho obs2
					   valor,
					   saldo;
	
	@Getter @Setter
	private DateTime dataPagamento = null, // vide cabecalho obs3
				     data;
	
	@Getter @Setter
	private int idContrato, id, 
		item; //vide obs4
	
	@Getter @Setter
	private Usuario tesoureiro;
	
	public Dados(){}	

	public Dados(
			int idProcesso,
			int item,
			String notaFiscal, 
			String tipoAditivo, 
			String numeroSei, 
			BigDecimal aditivo,
			BigDecimal valor, 
			BigDecimal saldo,
			Date dataPagamento, 
			Date dataRegistro, 
			Date referencia,
			int idContrato, 
			Usuario tesoureiro) {
		DateTime r = new DateTime(referencia);	
		
		this.id = idProcesso;
		this.item = item;
		this.notaFiscal = notaFiscal;
		this.tipoAditivo = tipoAditivo;
		this.numeroSei = numeroSei;
		this.ano = "" + r.getYear();
		this.mes = new FormatarCampo().intToMonth(r.getMonthOfYear());
		this.aditivo = aditivo;
		this.valor = valor;
		this.saldo = saldo;
		this.data = new DateTime(dataRegistro);
		this.idContrato = idContrato;
		this.tesoureiro = tesoureiro;
					
		if(dataPagamento != null){
			this.dataPagamento = new DateTime(dataPagamento);
		}
	}


	public Dados(String notaFiscal, String tipoAditivo, String numeroSei, String ano, String mes, BigDecimal aditivo,
			BigDecimal valor, BigDecimal saldo, Date dataRegistro, int idContrato) {
//		construtor usado para novos processos pois n�o tem data de pagamento
		this.notaFiscal = notaFiscal;
		this.tipoAditivo = tipoAditivo;
		this.numeroSei = numeroSei;
		this.ano = ano;
		this.mes = mes;
		this.aditivo = aditivo;
		this.valor = valor;
		this.saldo = saldo;
		this.data = new DateTime(dataRegistro);
		this.idContrato = idContrato;
	}
	
	public String getAditivoAsString(){
		return new FormatarCampo().decimalToString(this.aditivo);
	}
	
	public String getValorAsString(){
		return new FormatarCampo().decimalToString(this.valor);
	}
	
	public String getSaldoAsString() {
		return new FormatarCampo().decimalToString(this.saldo);
	}
	
	public String getDataPagamentoAsString(){
		if(dataPagamento != null)
			return this.dataPagamento.toString(formatoData);
		else
			return "";			
	}
	
	public String getDataAsString(){
		return this.data.toString(formatoData);
	}

	public String getMesAsInt() {
		return new FormatarCampo().mesToInt(mes);
	}	
}
