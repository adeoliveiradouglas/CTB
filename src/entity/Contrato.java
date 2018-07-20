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

import lombok.Data;

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
	
	private Date dataAssinatura,
				 dataOrdemServico,
				 dataGarantia,
				 dataVencimentoContrato,
				 dataVencimentoGarantia;
	
	private BigDecimal valorInicial,
				  	   valorAditivos = new BigDecimal(0),
				  	   valorTotal = new BigDecimal(0); //vide cabecalho obs5
	
	private ArrayList<Processo> processos;

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
		this.dataAssinatura = dataAssinatura;
		this.dataOrdemServico = dataOrdemServico;
		this.dataGarantia = dataGarantia;
		this.dataVencimentoContrato = dataVencimentoContrato;
		this.dataVencimentoGarantia = dataVencimentoGarantia;
		this.valorInicial = valorInicial;
	}
	
	public Contrato(
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
			ArrayList<Processo> processos) {
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
		this.dataAssinatura = dataAssinatura;
		this.dataOrdemServico = dataOrdemServico;
		this.dataGarantia = dataGarantia;
		this.dataVencimentoContrato = dataVencimentoContrato;
		this.dataVencimentoGarantia = dataVencimentoGarantia;
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
	}
	
	
}
