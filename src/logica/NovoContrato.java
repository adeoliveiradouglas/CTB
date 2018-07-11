package logica;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.Contrato;

public class NovoContrato implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		@SuppressWarnings("unused")
		String s = pedido.getParameter("assinatura");
		
		/*Contrato c = new Contrato(
			Integer.parseInt(pedido.getParameter("numero")),
			Integer.parseInt(pedido.getParameter("portaria")),
			Integer.parseInt(pedido.getParameter("gestor")),
			Integer.parseInt(pedido.getParameter("fiscal")),
			pedido.getParameter("nome"),
			pedido.getParameter("cnpjEmpresa"),	
			pedido.getParameter("nomeEmpresa"),
			pedido.getParameter("objeto"),
			pedido.getParameter("recurso"),
			pedido.getParameter("fontepagante"),
			pedido.getParameter("uso"),
			pedido.getParameter("assinatura"),
			pedido.getParameter("ordemDeServico"),
			pedido.getParameter("garantia"),
			pedido.getParameter("vencimento"),
			pedido.getParameter("vencimentoGarantia"),
			pedido.getParameter("valor")
		);*/
		
		return "sistema?logica=TelaPrincipalGestorGeral";
	}

}
