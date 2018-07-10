/*
 * Classe responsável por armazenar um contrato
 *
 *  Usando framework Lombok para gerar os getters e setters da classe através da anotação "@Data"
 *  obs2 - objeto: descricao do contrato
 *  obs3 - recurso, fontePagante e uso: há padrões para os três campos no bd, aqui só será armazenado o campo String/Varchar dos mesmos
 *  obs4 - guarda apenas a matricula da pessoa responsavel pelo contrato
 *  obs5 - valor total é o valor de todos os aditivos de todos os processos mais o valor inicial do contrato
 *  	   Em beta: calcular dinamicamente toda vez que o usuario pedir a pagina ou guardar o valor no banco e aumentar a cada aditivo 	
 */

package entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

import lombok.Data;

@Data
public class Contrato {
	private int numero,
				portaria,
				matriculaGestor, //vide cabecalho obs4
				matriculaFiscal; //vide cabecalho obs4
	
	private String nome,
				   cnpjEmpresaContratada,
				   nomeEmpresaContratada,
				   objeto, //vide cabecalho obs2
				   recurso, //vide cabecalho obs3
				   fontePagante, //vide cabecalho obs3
				   uso; //vide cabecalho obs3
	
	private Date dataAssinatura,
				 dataOrdemServico,
				 dataGarantia,
				 dataVencimentoContrato,
				 dataVencimentoGarantia;
	
	private BigDecimal valorInicial,
					   valorAditivos = new BigDecimal(0),
					   valorTotal; //vide cabecalho obs5
	
	private ArrayList<Processo> processos;

	public Contrato(int numero, int portaria, int matriculaGestor, int matriculaFiscal, String nome,
			String cnpjEmpresaContratada, String nomeEmpresaContratada, String objeto, String recurso,
			String fontePagante, String uso, Date dataAssinatura, Date dataOrdemServico, Date dataGarantia,
			Date dataVencimentoContrato, Date dataVencimentoGarantia, BigDecimal valorInicial) {
		super();
		this.numero = numero;
		this.portaria = portaria;
		this.matriculaGestor = matriculaGestor;
		this.matriculaFiscal = matriculaFiscal;
		this.nome = nome;
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
		this.valorTotal = valorInicial;
	}
	
	
	
}
