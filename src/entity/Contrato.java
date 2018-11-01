/*
 * Classe responsável por armazenar um contrato
 *
 *  Usando framework Lombok para gerar os getters e setters da classe através da anotação "@Getter" e "@Setter"  
 *  obs2 - objeto: descricao do contrato
 *  obs3 - recurso, fontePagante e uso: há padrões para os três campos no bd, aqui só será armazenado o campo String/Varchar dos mesmos
 *  obs5 - valor total é o valor de todos os aditivos de todos os dados mais o valor inicial do contrato
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
import utilidades.CalcularData;
import utilidades.FormatarCampo;
@ToString
public class Contrato {
	private static final String formatoData = "dd/MM/yyyy";
	FormatarCampo formatarCampo = new FormatarCampo();

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
	private List<Dados> dados;

	@Getter @Setter
	//para ajudar no controle de aviso quando email for enviado avisando sobre vencimento de contrato 
	public boolean avisado90, 
				   avisado60, 
				   avisado45;
	/*MÉTODOS*/
	public String getNome() {
		//parametro para comparação entre objetos
		return this.nomeEmpresaContratada;
	}
	
	public BigDecimal getSaldo() {
//		Retorna o saldo do último mês
		switch(this.dados.size()) {
			case 0:
//				caso o contrato não tenha dados atrelados a ele, o saldo é o valor inicial
				return this.valorInicial;
				
			default:
				return this.dados.get(0).getSaldo();
		}
	}
		
	public void recalcularSaldo(){
//		Recalcula e atualiza o saldo de todos os dados
		recalcularSaldo(this.dados.size() - 1);
	}
	
	public void recalcularSaldo(int apartir){
//		Recalcula e atualiza o saldo de todos os dados começando pela posicao "apartir" 
		BigDecimal saldo;
		Dados d;
		
		try {
			saldo = this.dados.get(apartir + 1).getSaldo();
		} catch (Exception e) {
//			caso o parâmetro "apartir" seja a última posição da lista, o saldo deve ser o valor inicial
			saldo = this.valorInicial;
		}
		
		for(int i = apartir; i >= 0; i--) {
			d = this.dados.get(i);
			
			saldo = saldo.add(d.getAditivo());
			saldo = saldo.subtract(d.getValor());	
			this.dados.get(i).setSaldo(
				saldo
			);
		}
	}
	
	public int getDiasParaVencimento() {
		return new CalcularData(dataVencimentoContrato).diasEntre();		
	}
	
	public int addDados(Dados p) {
//		Adiciona o objeto p na posicao correta com base na data de referencia e retorna a posicao em 
//		que foi inserido
		
		int posicao = 0;
		
		for (int i = this.dados.size() - 1; i >= 0; i--) {
			int mesLista = Integer.parseInt(this.dados.get(i).getMesAsInt()) , 
				mesParametro = Integer.parseInt(p.getMesAsInt()); 
				
			if(this.dados.get(i).getAno() == p.getAno() && mesParametro < mesLista) {
				//busca posição correta dos dados com base na data de referência
				posicao = i + 1;
				i = 0;									
			}
			
			else if(p.getAno() < this.dados.get(i).getAno()) {
				//isso só ocorre na primeira iteração
				posicao = this.dados.size();
				i = 0;
			}	
		}
		
		this.dados.add(posicao, p);
		recalcularSaldo(posicao);
		
		return posicao;
	}
	
	public void setValorAditivos(BigDecimal valorAditivos) {
		this.valorAditivos = valorAditivos;
		this.valorTotal = valorAditivos.add(this.valorInicial);
	}
	
	/*MÉTODOS DE FORMATAÇÃO DE STRING*/
	public String getValorInicialAsString(){
		return formatarCampo.decimalToString(this.valorInicial);
	}
	
	public String getValorAditivoAsString(){
		return formatarCampo.decimalToString(this.valorAditivos);
	}
	
	public String getValorTotalAsString(){
		return formatarCampo.decimalToString(this.valorTotal);
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
	
	
	/*CONSTRUTORES*/
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
//		Construtor usado para novos contratos pois não tem id do banco e nem entrada de dados
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
			List<Dados> dados, 
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
		this.dados = dados;
		this.avisado90 = avisado90;
		this.avisado60 = avisado60;
		this.avisado45 = avisado45;
		
//		Calcula valores aditivos e total
		BigDecimal aditivo = new BigDecimal("0");
		for (Dados p: dados){
//			Soma todos os aditivos de todos os dados dos contratos
			aditivo = aditivo.add(p.getAditivo());
		}
		
		this.valorAditivos = aditivo;
//		Soma o resultado do valor inicial com o valor dos aditivos e põe em valorTotal
		this.valorTotal = valorInicial.add(aditivo);
	}
}
