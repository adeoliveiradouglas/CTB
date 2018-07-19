/*
 * Classe responsável por armazenar dados de um processo
 * Usando framework Lombok para gerar os getters e setters da classe através da anotação "@Data"
 * Cada processo está dentro de um contrato.
 * obs1: numeroSei é o número do processo.
 * obs2: se valor do aditivo for nulo ou zero, então não houve aditivo.
 * obs3: se não houver data de pagamento, é porque ele ainda não foi feito.
 */


package entity;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Processo {
	private String notaFiscal,
			tipoAditivo = null, // vide cabecalho obs2
			numeroSei, // vide cabecalho obs1
			ano, mes;
	private BigDecimal aditivo = null,  // vide cabecalho obs2
					   valor; 
	private Date dataPagamento = null, // vide cabecalho obs3
				 dataProcesso;
	private int idContrato; 
	
	public Processo(){}

	

	public Processo(String notaFiscal, String tipoAditivo, String numeroSei, String ano, String mes, BigDecimal aditivo,
			BigDecimal valor, Date dataPagamento, Date dataProcesso, int idContrato) {
		this.notaFiscal = notaFiscal;
		this.tipoAditivo = tipoAditivo;
		this.numeroSei = numeroSei;
		this.ano = ano;
		this.mes = mes;
		this.aditivo = aditivo;
		this.valor = valor;
		this.dataPagamento = dataPagamento;
		this.dataProcesso = dataProcesso;
		this.idContrato = idContrato;
	}


	public Processo(String notaFiscal, String tipoAditivo, String numeroSei, String ano, String mes, BigDecimal aditivo,
			BigDecimal valor, Date dataProcesso, int idContrato) {
//		construtor usado para novos processos pois não tem data de pagamento
		this.notaFiscal = notaFiscal;
		this.tipoAditivo = tipoAditivo;
		this.numeroSei = numeroSei;
		this.ano = ano;
		this.mes = mes;
		this.aditivo = aditivo;
		this.valor = valor;
		this.dataProcesso = dataProcesso;
		this.idContrato = idContrato;
	}
	

	
	
}
