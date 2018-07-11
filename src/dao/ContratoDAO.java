package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Contrato;

public class ContratoDAO extends DAO{
	private final String colunaNumero = super.getNomeTabela() + ".numero",
			 			 colunaNome = super.getNomeTabela() + ".nome",
			 			 colunaEmpresaCnpj = super.getNomeTabela() + ".cnpjEmpresaContratada",
			 			 colunaEmpresaNome = super.getNomeTabela() + ".nomeEmpresaContratada",
			 			 colunaPortaria = super.getNomeTabela() + ".portaria",
			 			 colunaDataAssinatura = super.getNomeTabela() + ".dataAssinatura",
			 			 colunaDataOrdemServico = super.getNomeTabela() + ".dataOrdemServico",
			 			 colunaDataGarantia = super.getNomeTabela() + ".dataGarantia",
			 			 colunaValorInicial = super.getNomeTabela() + ".valorInicial",
					 	 colunaValorTotal = super.getNomeTabela() + ".valorTotal",
					 	 colunaValorAditivos = super.getNomeTabela() + ".valorAditivos",
					 	 colunaDataVencimentoContrato = super.getNomeTabela() + ".dataVencimentoContrato",
					 	 colunaDataVencimentoGarantia = super.getNomeTabela() + ".dataVencimentoGarantia",
					 	 colunaObjeto = super.getNomeTabela() + ".objeto",
					 	 colunaGestor = super.getNomeTabela() + ".gestor",
					 	 colunaFiscal = super.getNomeTabela() + ".fiscal",
					 	 colunaRecurso = super.getNomeTabela() + ".recurso_id",
					 	 colunaFontePagante = super.getNomeTabela() + ".fontePagante_id",
					 	 colunaUso = super.getNomeTabela() + ".uso_id",
					 	 tabelaRecurso = "recurso",
					 	 tabelaFontePagante = "fontepagante",
					 	 tabelaUso = "uso";
	
	
	public ContratoDAO() {
		super("contrato");
	}
	
	public ArrayList<Contrato> getByGestor(int matricula){
		iniciaConexaoComBanco();
		
//		Exemplo: select * from contrato where gestor = matricula
		super.setSqlQuery(
			"select * from " + super.getNomeTabela() + " where " + colunaGestor + " = ?"
		);
		
		try {
			super.setStatement(
				super.getDbConnection().prepareStatement(
					super.getSqlQuery()
				)
			);
			
			super.getStatement().setInt(
				1, 
				matricula
			);
			
			super.setResultado(
				super.getStatement().executeQuery()
			);
		} catch (SQLException e) {
			e.printStackTrace();
			encerraConexaocomBanco();
			return null;
		}

		ArrayList<Contrato> lista = new ArrayList<Contrato>();
		Contrato c = null;
		
		try {
			while (super.getResultado().next()){
				c = new Contrato(
					super.getResultado().getInt(colunaNumero),
					super.getResultado().getInt(colunaPortaria),
					super.getResultado().getInt(colunaGestor),
					super.getResultado().getInt(colunaFiscal),
					super.getResultado().getString(colunaNome),
					super.getResultado().getString(colunaEmpresaCnpj),	
					super.getResultado().getString(colunaEmpresaNome),
					super.getResultado().getString(colunaObjeto),
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
				
				lista.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			encerraConexaocomBanco();
			return null;
		}
		
		encerraConexaocomBanco();
		return lista;
	}
	
	public ArrayList<Contrato> getAllRecente(int quantidade){
		ArrayList<Contrato> recentes = new ArrayList<Contrato>();
		
		return recentes;
	}

	public ArrayList<Contrato> getVencimento90() {
		ArrayList<Contrato> recentes = new ArrayList<Contrato>();
		
		return recentes;
	}
}
