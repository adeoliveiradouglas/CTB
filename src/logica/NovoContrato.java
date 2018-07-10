package logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OutroDAO;
import entity.Contrato;

public class NovoContrato implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		Contrato c = new Contrato(
			pedido.getParameter("numero"),
			pedido.getParameter("portaria"),
			pedido.getParameter("gestor"),
			pedido.getParameter("fiscal"),
			pedido.getParameter("nome"),
			pedido.getParameter("cnpjEmpresa"),	
			pedido.getParameter("nomeEmpresa"),
			pedido.getParameter("objeto"),
			pedido.getParameter("")
			new OutroDAO(tabelaRecurso).getById(
				super.getResultado().getInt(colunaRecurso)
			).getNome(),
			new OutroDAO(tabelaFontePagante).getById(
				super.getResultado().getInt(colunaFontePagante)
			).getNome(),
			new OutroDAO(tabelaUso).getById(
				super.getResultado().getInt(colunaUso)
			).getNome(),
			super.getResultado().getDate(colunaDataAssinatura),
			super.getResultado().getDate(colunaDataOrdemServico),
			super.getResultado().getDate(colunaDataGarantia),
			super.getResultado().getDate(colunaDataVencimentoContrato),
			super.getResultado().getDate(colunaDataVencimentoGarantia),
			super.getResultado().getBigDecimal(colunaValorInicial)
		);
		return "sistema?logica=TelaPrincipalGestorGeral";
	}

}
