package web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Contrato;
import entity.Dados;
import entity.Resumo;
import utilidades.FormatarCampo;

public class VerResumoContrato implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		Contrato contrato = (Contrato) pedido.getSession().getAttribute("contratoVisualizar");
		List<Dados> processos = contrato.getDados();
		List<Resumo> anos = new ArrayList<Resumo>(); //anos que o resumo deve abranger
		BigDecimal totalPago = new BigDecimal(0);
		
		Collections.reverse(processos);
		anos.add(new Resumo(""+processos.get(0).getAno())); //cria registro do primeiro ano 
		int posicao = anos.size() - 1;
		anos.get(posicao).setSaldo(processos.get(0).getSaldo());
		
		
		//calcula o resumo ano a ano
		for (Dados d: processos) {
			
			/*
			 * verificar se o ano de "d" é o mesmo do mês anterior
			 * se sim, soma o valor do mês ao total do ano e atualiza o saldo para manter o último no registro
			 * se não, cria um novo registro de resumo de um novo ano
			 * todo novo ano é inserido no final da lista 
			 */
			if (!anos.get(posicao).getMarcacao().equals(""+d.getAno())) {
				anos.add(new Resumo(
					d.getAno()+"",
					anos.get(posicao).getTotalAcumulado(),
					anos.get(posicao).getAditivoAcumulado()
					)
				);
				posicao++;
			}
						
			//sempre acessa o ano mais recente que está na última posição da lista para atualizar os registros
			anos.get(posicao).somaTotal(d.getValor());
			anos.get(posicao).somaAditivo(d.getAditivo());
			anos.get(posicao).setSaldo(d.getSaldo());
			anos.get(posicao).getMeses().add(
				new Resumo(
					d.getMes(),
					d.getValor(),
					d.getAditivo(),
					d.getSaldo()
				)
			);
			
			totalPago = totalPago.add(d.getValor());
		}				

		//calcular porcentagem concluída e a concluir
		int porcentagemConcluida = 0, aConcluir;
		try {
//			porcentagem = totalPago / valorDoContrato * 100
			porcentagemConcluida = Math.round(totalPago.floatValue() / contrato.getValorTotal().floatValue() * 100);
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
		
/*	
 * 		GAMBIARRA ABAIXO
 * 		se não reverter novamente a list "processos", no próximo pedido ela já vem como invertida
 */
		Collections.reverse(processos);
		return "/Comum/resumoDoContrato.jsp";
	}
}
