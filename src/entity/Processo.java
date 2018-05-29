/*
 * Classe respons�vel por armazenar dados de um processo
 * Usando framework Lombok para gerar os getters e setters da classe atrav�s da anota��o "@Data"
 * Cada processo est� dentro de um contrato.
 * obs1: numeroSei � o n�mero do contrato.
 * obs2: se valor do aditivo for nulo ou zero, ent�o n�o houve aditivo.
 * obs3: se n�o houver data de pagamento, � porque ele ainda n�o foi feito.
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
