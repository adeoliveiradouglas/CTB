package logica;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProcessoDAO;
import entity.Processo;
import utilidades.StringToDecimal;

public class CadastrarProcesso implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		Date dataProcesso = new SimpleDateFormat("yyyy-MM-dd").parse(
				pedido.getParameter("data")
			);
		
		String v = new StringToDecimal().formatarParaBanco(pedido.getParameter("valor")),
			   va = new StringToDecimal().formatarParaBanco(pedido.getParameter("valorAditivo"));
		
		BigDecimal valor = null, aditivo = null;
		
		try{
			valor = new BigDecimal(v); 
			aditivo = new BigDecimal(va);
		} catch (NumberFormatException e){
			//catch para não dar erro no processo quando não houver aditivo
			aditivo = new BigDecimal("0.00");
		}
		
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
		return "sistema?logica=TelaPrincipalGestor";
	}
	
	
}
