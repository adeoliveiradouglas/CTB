package logica;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProcessoDAO;
import entity.Processo;

public class CadastrarProcesso implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		Date dataProcesso = new SimpleDateFormat("yyyy-MM-dd").parse(
				pedido.getParameter("data")
			);
				
		BigDecimal valor = new BigDecimal(
				formatarValor(
					pedido.getParameter("valor")
				)
			), 
			aditivo = new BigDecimal(
				formatarValor(
					pedido.getParameter("valorAditivo")
				)
			);
		
		Processo p = new Processo(
				pedido.getParameter("notaFiscal"),
				pedido.getParameter("tipoAditivo"),
				pedido.getParameter("numero"),
				pedido.getParameter("ano"),
				pedido.getParameter("mes"),
				aditivo,
				valor,
				dataProcesso,
				Integer.parseInt(pedido.getParameter("idContrato"))
			);
		
		new ProcessoDAO().inserir(p);
		return "sistema?logica=TelaPrincipal";
	}
	
	private String formatarValor(String parameter) {
//		Tirar pontos do valor e mudar vírgula para ponto
		parameter = parameter.replace(".", "");
		parameter = parameter.replace(",", ".");
		
		return parameter;
	}
}
