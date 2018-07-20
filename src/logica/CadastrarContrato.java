package logica;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContratoDAO;
import dao.OutroDAO;
import dao.UsuarioDAO;
import entity.Contrato;
import entity.Outro;
import entity.Usuario;
import utilidades.StringToDecimal;

public class CadastrarContrato implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		final String formatoData = "yyyy-MM-dd";
		
		Date assinatura = new SimpleDateFormat(formatoData).parse(
				pedido.getParameter("dataAssinatura")
			),
			ordemDeServico = new SimpleDateFormat(formatoData).parse(
				pedido.getParameter("dataOs")
			),
			garantia = new SimpleDateFormat(formatoData).parse(
				pedido.getParameter("dataGarantia")
			),
			vencimento = new SimpleDateFormat(formatoData).parse(
				pedido.getParameter("dataVencimento")
			),
			vencimentoGarantia = new SimpleDateFormat(formatoData).parse(
				pedido.getParameter("dataVencimentoGarantia")
			);
		
		Usuario gestor = new UsuarioDAO().getById(Integer.parseInt(pedido.getParameter("gestor"))),
				fiscal = new UsuarioDAO().getById(Integer.parseInt(pedido.getParameter("fiscal")));
		
		BigDecimal valor = new BigDecimal(
			new StringToDecimal().formatarParaBanco(
				pedido.getParameter("valor")
			)
		);
		
		Outro recurso = new OutroDAO("recurso").getById(
			Integer.parseInt(pedido.getParameter("recurso"))
		),
		fontepagante = new OutroDAO("fontepagante").getById(
			Integer.parseInt(pedido.getParameter("fontepagante"))
		),
		uso = new OutroDAO("uso").getById(
			Integer.parseInt(pedido.getParameter("uso"))
		);
		
		Contrato c = new Contrato(
			pedido.getParameter("numero"),
			Integer.parseInt(pedido.getParameter("portaria")),
			gestor,
			fiscal,
			pedido.getParameter("cnpjEmpresa"),	
			pedido.getParameter("nomeEmpresa"),
			pedido.getParameter("objeto"),
			recurso,
			fontepagante,
			uso,
			assinatura,
			ordemDeServico,
			garantia,
			vencimento,
			vencimentoGarantia,
			valor
		);
		
		new ContratoDAO().inserir(c);
		return "sistema?logica=TelaPrincipalGestorGeral";
	}



}
