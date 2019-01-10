package web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Contrato;
import entity.Dados;
import entity.ResumoAno;
import utilidades.FormatarCampo;

public class VerResumoContrato implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		Contrato contrato = (Contrato) pedido.getSession().getAttribute("contratoVisualizar");
		List<Dados> processos = contrato.getDados();
		List<ResumoAno> anos = new ArrayList<ResumoAno>(); //anos que o resumo deve abranger
		BigDecimal totalPago = new BigDecimal(0);
		
		anos.add(new ResumoAno(processos.get(0).getAno())); //cria registro do primeiro ano 
		anos.get(anos.size() - 1).setSaldo(processos.get(0).getSaldo());
		
		//calcula o resumo ano a ano
		for (Dados d: processos) {
			/*
			 * verificar se o ano de "d" é o mesmo do mês anterior
			 * se sim, soma o valor do mês ao total do ano e atualiza o saldo para manter o último no registro
			 * se não, cria um novo registro de resumo de um novo ano
			 * todo novo ano é inserido no final da lista 
			 */
			if (d.getAno() != anos.get(anos.size() - 1).getAno()) {
				anos.get(anos.size() - 1).setSaldo(d.getSaldo());
				anos.add(new ResumoAno(d.getAno()));
			}
						
			//sempre acessa o ano mais recente que está na última posição da lista para atualizar os registros
			anos.get(anos.size() - 1).somaTotal(d.getValor());
			anos.get(anos.size() - 1).somaAditivo(d.getAditivo());
			
			totalPago = totalPago.add(d.getValor());
		}				

		//calcular porcentagem concluída e a concluir
		float porcentagemConcluida = 0, aConcluir;
		try {
//			porcentagem = totalPago / valorDoContrato * 100
			porcentagemConcluida = totalPago.floatValue() / contrato.getValorTotal().floatValue() * 100;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		aConcluir = 100 - porcentagemConcluida;
		FormatarCampo fc = new FormatarCampo();
		
		pedido.setAttribute("anos", anos);
		pedido.setAttribute("porcentagemConcluida", porcentagemConcluida);
		pedido.setAttribute("porcentagemAConcluir", aConcluir);
		pedido.setAttribute("valorTotalPago", fc.decimalToString(totalPago));
		pedido.setAttribute(
			"valorAPagar", 
			fc.decimalToString( //converte para string o resultado de valorDoContrato MENOS (-) valorJáPago
				contrato.getValorTotal().subtract(totalPago)
			)
		);
		
		return "/Comum/resumoDoContrato.jsp";
	}
}
