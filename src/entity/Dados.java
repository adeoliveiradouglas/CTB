/*
 * Classe responsï¿½vel por armazenar dados de um processo
 * Usando framework Lombok para gerar os getters e setters da classe atravï¿½s da anotaï¿½ï¿½o "@Data"
 * Cada processo estï¿½ dentro de um contrato.
 * obs1: numeroSei ï¿½ o nï¿½mero do processo.
 * obs2: se valor do aditivo for nulo ou zero, entï¿½o nï¿½o houve aditivo.
 * obs3: se nï¿½o houver data de pagamento, ï¿½ porque ele ainda nï¿½o foi feito.
 * obs4: posiï¿½ï¿½o de contagem do processo no contrato
 */


package entity;

import java.math.BigDecimal;
import java.util.Date;

import org.joda.time.DateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import utilidades.FormatarCampo;

@ToString
@NoArgsConstructor
public class Dados {
	private static final String formatoData = "dd/MM/yyyy";
	FormatarCampo formatarCampo = new FormatarCampo();

	@Getter @Setter
	private String notaFiscal,
			tipoAditivo = null, // vide cabecalho obs2
			numeroSei, // vide cabecalho obs1
			mes;
	
	@Getter @Setter
	private BigDecimal aditivo = null,  // vide cabecalho obs2
					   valor,
					   saldo;
	
	@Getter @Setter
	private DateTime dataPagamento = null, // vide cabecalho obs3
				     data;
	
	@Getter @Setter
	private int idContrato, id, 
		ano, 
		item; //vide obs4
	
	@Getter @Setter
	private Usuario tesoureiro;	

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
		this.ano = r.getYear();
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


	public Dados(String notaFiscal, String tipoAditivo, String numeroSei, int ano, String mes, BigDecimal aditivo,
			BigDecimal valor, BigDecimal saldo, Date dataRegistro, int idContrato) {
//		construtor usado para novos processos pois não tem data de pagamento
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
		return formatarCampo.decimalToString(this.aditivo);
	}
	
	public String getValorAsString(){
		return formatarCampo.decimalToString(this.valor);
	}
	
	public String getSaldoAsString() {
		return formatarCampo.decimalToString(this.saldo);
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
		return formatarCampo.mesToInt(mes);
	}	
}
