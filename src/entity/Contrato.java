/*
 * Classe responsável por armazenar um contrato
 *
 *  Usando framework Lombok para gerar os getters e setters da classe através da anotação "@Data"
 *  obs2 - objeto: descricao do contrato
 *  obs3 - recurso, fontePagante e uso: há padrões para os três campos no bd, aqui só será armazenado o campo String/Varchar dos mesmos
 *  obs5 - valor total é o valor de todos os aditivos de todos os processos mais o valor inicial do contrato
 *  	   Calcula somente quando é retorno de resultado do banco 	
 */

package entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.joda.time.DateTime;

import lombok.Data;
import utilidades.FormatarCampo;

@Data
public class Contrato {
	private int id,
				portaria;
	
	private Usuario	gestor, 
					fiscal;
	
	private Outro recurso, //vide cabecalho obs3
	   			  fontePagante, //vide cabecalho obs3
	   			  uso; //vide cabecalho obs3
	
	private String numero,
				   cnpjEmpresaContratada,
				   nomeEmpresaContratada,
				   objeto; //vide cabecalho obs2				   
	
	private DateTime dataAssinatura,
				 dataOrdemServico,
				 dataGarantia,
				 dataVencimentoContrato,
				 dataVencimentoGarantia;
	
	private BigDecimal valorInicial,
				  	   valorAditivos = new BigDecimal(0),
				  	   valorTotal = new BigDecimal(0); //vide cabecalho obs5
	
	private ArrayList<Processo> processos;

	//para ajudar no controle de aviso quando email for enviado avisando sobre vencimento de contrato 
	public boolean avisado90, 
				   avisado60, 
				   avisado45;

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
		this.avisado45 = this.avisado60 = this.avisado90 == false;
	}
	
/*	public Contrato(
			int id,
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
			BigDecimal valorInicial,
			ArrayList<Processo> processos
			) {
//		Construtor com cálculos de aditivos
		this.id = id;
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
		this.processos = processos;
		
//		Calcula valores aditivos e total
		BigDecimal aditivo = new BigDecimal("0");
		for (Processo p: processos){
//			Soma todos os aditivos de todos os processos dos contratos
			aditivo = aditivo.add(
				p.getAditivo()
			);
		}
		
		this.valorAditivos = aditivo;
//		Soma o resultado do valor inicial com o valor dos aditivos e põe em valorTotal
		this.valorTotal = valorInicial.add(aditivo);
	}*/
	
	public String getValorInicialAsString(){
		return new FormatarCampo().decimalToString(this.valorInicial);
	}
	
	public String getValorAditivoAsString(){
		return new FormatarCampo().decimalToString(this.valorAditivos);
	}
	
	public String getValorTotalAsString(){
		return new FormatarCampo().decimalToString(this.valorTotal);
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
			ArrayList<Processo> processos, 
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
		for (Processo p: processos){
//			Soma todos os aditivos de todos os processos dos contratos
			aditivo = aditivo.add(p.getAditivo());
		}
		
		this.valorAditivos = aditivo;
//		Soma o resultado do valor inicial com o valor dos aditivos e põe em valorTotal
		this.valorTotal = valorInicial.add(aditivo);
	}
	
}
