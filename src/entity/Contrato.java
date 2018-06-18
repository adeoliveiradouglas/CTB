/*
 * Classe responsável por armazenar um contrato
 *
 *  Usando framework Lombok para gerar os getters e setters da classe através da anotação "@Data"
 *  obs1 - tempoVigenteDias: se verdadeiro, deve calcular o tempo vigente para vencimento como dias. Se falso, calcular como meses.
 *  obs2 - objeto: descricao do contrato
 *  obs3 - recurso, fontePagante e uso: há padrões para os três campos no bd, aqui só será armazenado o campo String/Varchar dos mesmos
 *  obs4 - guarda apenas a matricula da pessoa responsavel pelo contrato
 *  obs5 - valor total é o valor de todos os aditivos de todos os processos mais o valor inicial do contrato 
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
				tempoVigente, //vide cabecalho obs1
				matriculaGestor, //vide cabecalho obs4
				matriculaFiscal; //vide cabecalho obs4
	
	private boolean tempoVigenteDias; //vide cabecalho obs1
	
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
				 dataVencimentoContrato, //vide cabecalho obs1
				 dataVencimentoGarantia;
	
	private BigDecimal valorInicial,
					   valorAditivos,
					   valorTotal; //vide cabecalho obs5
	
	private ArrayList<Processo> processos;
	
	public Contrato(){}
	
}
