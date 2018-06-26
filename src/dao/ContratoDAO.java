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
					 	 colunaFontePagante = super.getNomeTabela() + ".fontePagante_id",
					 	 colunaUso = super.getNomeTabela() + ".uso_id",
					 	 tabelaRecurso = "recurso",
					 	 tabelaFontePagante = "fontepagante",
					 	 tabelaUso = "uso";
	
	
	public ContratoDAO(String tabelaDB) {
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
				c = new Contrato();
				
				c.setNumero(
					super.getResultado().getInt(colunaNumero)
				);
				
				c.setNome(
					super.getResultado().getString(colunaNome)
				);
				
				c.setCnpjEmpresaContratada(
					super.getResultado().getString(colunaEmpresaCnpj)	
				);
				
				c.setNomeEmpresaContratada(
					super.getResultado().getString(colunaEmpresaNome)
				);
				
				c.setPortaria(
					super.getResultado().getInt(colunaPortaria)
				);
				
				c.setDataAssinatura(
					super.getResultado().getDate(colunaDataAssinatura)
				);
				
				c.setDataOrdemServico(
					super.getResultado().getDate(colunaDataOrdemServico)
				);
				
				c.setDataGarantia(
					super.getResultado().getDate(colunaDataGarantia)
				);
				
				c.setTempoVigente(
					super.getResultado().getInt(colunaTempoVigente)
				);
				
				c.setTempoVigenteDias(
					super.getResultado().getBoolean(colunaTempoVigenteDias)
				);
				
				c.setValorInicial(
					super.getResultado().getBigDecimal(colunaValorInicial)
				);
				
				c.setValorTotal(
					super.getResultado().getBigDecimal(colunaValorTotal)
				);
				
				c.setValorAditivos(
					super.getResultado().getBigDecimal(colunaValorAditivos)
				);
				
				c.setDataVencimentoContrato(
					super.getResultado().getDate(colunaDataVencimentoContrato)
				);
				
				c.setDataVencimentoGarantia(
					super.getResultado().getDate(colunaDataVencimentoGarantia)
				);
				
				c.setObjeto(
					super.getResultado().getString(colunaObjeto)
				);
				
				c.setMatriculaGestor(
					super.getResultado().getInt(colunaGestor)
				);
				
				c.setMatriculaFiscal(
					super.getResultado().getInt(colunaFiscal)
				);
				
				c.setRecurso(
					new OutroDAO(tabelaRecurso).getById(
						super.getResultado().getInt(colunaRecurso)
					).getNome()
				);
				
				c.setFontePagante(
					new OutroDAO(tabelaFontePagante).getById(
						super.getResultado().getInt(colunaFontePagante)
					).getNome()
				);
				
				c.setUso(
					new OutroDAO(tabelaUso).getById(
						super.getResultado().getInt(colunaUso)
					).getNome()
				);
				
				c.setProcessos(
					new ProcessoDAO().getByContrato(c.getNumero())
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			encerraConexaocomBanco();
			return null;
		}
		
		encerraConexaocomBanco();
		return lista;
	}

}
