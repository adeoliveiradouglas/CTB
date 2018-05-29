/*
 * Classe responsável por armazenar dados de um processo
 * Usando framework Lombok para gerar os getters e setters da classe através da anotação "@Data"
 * Cada processo está dentro de um contrato.
 * obs1: numeroSei é o número do contrato.
 * obs2: se valor do aditivo for nulo ou zero, então não houve aditivo.
 * obs3: se não houver data de pagamento, é porque ele ainda não foi feito.
 */


package entity;

import lombok.Data;
import java.math.BigDecimal;
import java.sql.Date;

@Data
public class Processo {
	private int id;
	private String notaFiscal,
			tipoAditivo,
			numeroSei; // vide cabecalho obs1
	private BigDecimal aditivo; // vide cabecalho obs2
	private Date dataPagamento; // vide cabecalho obs3
}
