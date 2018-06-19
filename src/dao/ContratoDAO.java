package dao;

public class ContratoDAO extends DAO{
	private final String colunaNumero = super.getNomeTabela() + ".numero",
			 			 colunaNome = super.getNomeTabela() + ".nome",
			 			 colunaCnpjEmpresa = super.getNomeTabela() + ".cnpjEmpresaContratada",
			 			 colunaPortaria = super.getNomeTabela() + ".portaria",
			 			 colunaDataAssintura = super.getNomeTabela() + ".dataAssinatura",
			 			 colunaDataOrdemServico = super.getNomeTabela() + ".dataOrdemServico",
			 			 colunaDataGarantia = super.getNomeTabela() + ".dataGarantia",
			 			 colunaTempoVigente = super.getNomeTabela() + ".tempoVigente",
					 	 colunaTempoVigenteDias = super.getNomeTabela() + ".tempoVigenteDias",
					 	 colunaValorInicial = super.getNomeTabela() + ".valorInicial",
					 	 colunaValorTotal = super.getNomeTabela() + ".valorTotal",
					 	 colunaValorAditivos = super.getNomeTabela() + ".valorAditivos",
					 	 colunaDataVencimentoContrato = super.getNomeTabela() + ".dataVencimentoContrato",
					 	 colunaDataVencimentoGarantia = super.getNomeTabela() + ".dataVencimentoGarantia",
					 	 colunaObjeto = super.getNomeTabela() + ".objeto",
					 	 colunaGestor = super.getNomeTabela() + ".gestor",
					 	 colunaFiscal = super.getNomeTabela() + ".fiscal",
					 	 colunaRecurso = super.getNomeTabela() + ".recurso_id",
					 	 colunaFontePagante = super.getNomeTabela() + ".fontaPagante_id",
					 	 colunaUso = super.getNomeTabela() + ".uso_id";
	
	
	public ContratoDAO(String tabelaDB) {
		super("contrato");
	}

}
