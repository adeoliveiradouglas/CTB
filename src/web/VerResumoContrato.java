package web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Contrato;
import entity.Dados;
import entity.ResumoAno;

public class VerResumoContrato implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		Contrato contrato = (Contrato) pedido.getSession().getAttribute("contratoVisualizar");
		List<Dados> processos = contrato.getDados();
		List<ResumoAno> anos = new ArrayList<ResumoAno>(); //anos que o resumo deve abranger
		anos.add(new ResumoAno(processos.get(0).getAno())); //cria registro do primeiro ano 
		
		//calcula o resumo ano a ano
		for (Dados d: processos) {
			/*
			 * verificar se o ano de "d" � o mesmo do m�s anterior
			 * se sim, soma o valor do m�s ao total do ano e atualiza o saldo para manter o �ltimo no registro
			 * se n�o, cria um novo registro de resumo de um novo ano
			 * todo novo ano � inserido no final da lista 
			 */
			if (d.getAno() != anos.get(anos.size() - 1).getAno()) {
				anos.add(new ResumoAno(d.getAno()));
			}
						
			//sempre acessa o ano mais recente que est� na �ltima posi��o da lista para atualizar os registros
			anos.get(anos.size() - 1).setSaldo(d.getSaldo());
			anos.get(anos.size() - 1).somaTotal(d.getValor());
			anos.get(anos.size() - 1).somaAditivo(d.getAditivo());
		}				

		//calcular porcentagem conclu�da e a concluir
		BigDecimal totalPago = new BigDecimal(0);
		
		for (ResumoAno r: anos) { //calcula o total j� pago
			totalPago = totalPago.add(r.getTotal());
		}

		BigDecimal cem = new BigDecimal(100);
		BigDecimal porcentagemConcluida = totalPago.divide(contrato.getValorTotal());
		porcentagemConcluida.multiply(cem);
		BigDecimal aConcluir = porcentagemConcluida.subtract(cem);
		
		pedido.setAttribute("anos", anos);
		pedido.setAttribute("concluida", porcentagemConcluida);
		pedido.setAttribute("aConcluir", aConcluir);
		
		return "/Comum/resumoDoContrato.jsp";
	}
}
