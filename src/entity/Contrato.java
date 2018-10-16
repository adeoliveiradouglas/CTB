/*
 * Classe responsável por armazenar um contrato
 *
 *  Usando framework Lombok para gerar os getters e setters da classe através da anotação "@Getter" e "@Setter"  
 *  obs2 - objeto: descricao do contrato
 *  obs3 - recurso, fontePagante e uso: há padrões para os três campos no bd, aqui só será armazenado o campo String/Varchar dos mesmos
 *  obs5 - valor total é o valor de todos os aditivos de todos os processos mais o valor inicial do contrato
 *  	   Calcula somente quando é retorno de resultado do banco 	
 */

package entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import utilidades.FormatarCampo;
@ToString
public class Contrato {
	private static final String formatoData = "dd/MM/yyyy";
	
	@Getter @Setter 
	private int id,
				portaria;
	
	@Getter @Setter
	private Usuario	gestor, 
					fiscal;
	
	@Getter @Setter
	private Outro recurso, //vide cabecalho obs3
	   			  fontePagante, //vide cabecalho obs3
	   			  uso; //vide cabecalho obs3
	
	@Getter @Setter
	private String numero,
				   cnpjEmpresaContratada,
				   nomeEmpresaContratada,
				   objeto; //vide cabecalho obs2				   
	
	@Getter @Setter
	private DateTime dataAssinatura,
				 dataOrdemServico,
				 dataGarantia,
				 dataVencimentoContrato,
				 dataVencimentoGarantia;
	
	@Getter
	private BigDecimal valorInicial,
				  	   valorAditivos = new BigDecimal(0),
				  	   valorTotal = new BigDecimal(0); //vide cabecalho obs5
	
	@Getter @Setter
	private List<Dados> processos;

	@Getter @Setter
	//para ajudar no controle de aviso quando email for enviado avisando sobre vencimento de contrato 
	public boolean avisado90, 
				   avisado60, 
				   avisado45;
	/*MÉTODOS*/
	public void setValorAditivos(BigDecimal valorAditivos) {
		this.valorAditivos = valorAditivos;
		this.valorTotal = valorAditivos.add(this.valorInicial);
	}
	
	public String getValorInicialAsString(){
		return new FormatarCampo().decimalToString(this.valorInicial);
	}
	
	public String getValorAditivoAsString(){
		return new FormatarCampo().decimalToString(this.valorAditivos);
	}
	
	public String getValorTotalAsString(){
		return new FormatarCampo().decimalToString(this.valorTotal);
	}
	
	public String getDataAssinaturaAsString(){
		return this.dataAssinatura.toString(formatoData);
	}
	
	public String getDataOrdemServicoAsString(){
		return this.dataOrdemServico.toString(formatoData);
	}
	
	public String getDataGarantiaAsString(){
		return this.dataGarantia.toString(formatoData);
	}
	
	public String getDataVencimentoContratoAsString(){
		return this.dataVencimentoContrato.toString(formatoData);
	}
	
	public String getDataVencimentoGarantiaAsString(){
		return this.dataVencimentoGarantia.toString(formatoData);
	}
	
	public String getNome() {
		//parametro para comparação entre objetos
		return this.nomeEmpresaContratada;
	}
	
	public BigDecimal getSaldo() {
		int ultimo = this.processos.size() - 1;
		
		switch(ultimo) {
			case -1:
//				caso o contrato não tenha processos atrelados a ele, o saldo é o valor inicial
				return this.valorTotal;
				
			default:
				return this.processos.get(ultimo).getSaldo();
		}
	}
	
	public BigDecimal getSaldo(int i) {
		return this.processos.get(i).getSaldo();
	}
	
	public Contrato(int id, boolean avisado90, boolean avisado60, boolean avisado45) {
		this.id = id;
		this.avisado90 = avisado90;
		this.avisado60 = avisado60;
		this.avisado45 = avisado45;
	}
	
	public Contrato(
			String numero, 
			int portaria,
			Usuario gestor, 
			Usuario fiscal,
			String cnpjEmpresaContratada, 
			String nomeEmpresaContratada, 
			String objeto, 
			Outro recurso,
			Outro fontePagante, 
			Outro uso, 
			Date dataAssinatura, 
			Date dataOrdemServico, 
			Date dataGarantia,
			Date dataVencimentoContrato, 
			Date dataVencimentoGarantia, 
			BigDecimal valorInicial) {
//		Construtor usado para novos contratos pois não tem id do banco e nem entrada de processos
		this.numero = numero;
		this.portaria = portaria;
		this.gestor = gestor;
		this.fiscal = fiscal;
		this.cnpjEmpresaContratada = cnpjEmpresaContratada;
		this.nomeEmpresaContratada = nomeEmpresaContratada;
		this.objeto = objeto;
		this.recurso = recurso;
		this.fontePagante = fontePagante;
		this.uso = uso;
		this.dataAssinatura = new DateTime(dataAssinatura);
		this.dataOrdemServico = new DateTime(dataOrdemServico);
		this.dataGarantia = new DateTime(dataGarantia);
		this.dataVencimentoContrato = new DateTime(dataVencimentoContrato);
		this.dataVencimentoGarantia = new DateTime(dataVencimentoGarantia);
		this.valorInicial = valorInicial;
		this.avisado45 = this.avisado60 = this.avisado90 = false;
	}
	
	public Contrato(
			int id, 
			int portaria, 
			Usuario gestor, 
			Usuario fiscal, 
			Outro recurso, 
			Outro fontePagante, 
			Outro uso,
			String numero, 
			String cnpjEmpresaContratada, 
			String nomeEmpresaContratada, 
			String objeto,
			Date dataAssinatura, 
			Date dataOrdemServico,
			Date dataGarantia, 
			Date dataVencimentoContrato,
			Date dataVencimentoGarantia, 
			BigDecimal valorInicial,
			List<Dados> processos, 
			boolean avisado90, 
			boolean avisado60, 
			boolean avisado45) {
		super();
		this.id = id;
		this.portaria = portaria;
		this.gestor = gestor;
		this.fiscal = fiscal;
		this.recurso = recurso;
		this.fontePagante = fontePagante;
		this.uso = uso;
		this.numero = numero;
		this.cnpjEmpresaContratada = cnpjEmpresaContratada;
		this.nomeEmpresaContratada = nomeEmpresaContratada;
		this.objeto = objeto;
		this.dataAssinatura = new DateTime(dataAssinatura);
		this.dataOrdemServico = new DateTime(dataOrdemServico);
		this.dataGarantia = new DateTime(dataGarantia);
		this.dataVencimentoContrato = new DateTime(dataVencimentoContrato);
		this.dataVencimentoGarantia = new DateTime(dataVencimentoGarantia);
		this.valorInicial = valorInicial;
		this.processos = processos;
		this.avisado90 = avisado90;
		this.avisado60 = avisado60;
		this.avisado45 = avisado45;
		
//		Calcula valores aditivos e total
		BigDecimal aditivo = new BigDecimal("0");
		for (Dados p: processos){
//			Soma todos os aditivos de todos os processos dos contratos
			aditivo = aditivo.add(p.getAditivo());
		}
		
		this.valorAditivos = aditivo;
//		Soma o resultado do valor inicial com o valor dos aditivos e põe em valorTotal
		this.valorTotal = valorInicial.add(aditivo);
	}
}
