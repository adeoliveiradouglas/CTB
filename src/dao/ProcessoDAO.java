package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Processo;

public class ProcessoDAO extends DAO{
	private final String colunaNotaFiscal = super.getNomeTabela() + ".notaFiscal",
						 colunaAditivo = super.getNomeTabela() + ".aditivo",
						 colunaTipoAditivo = super.getNomeTabela() + ".tipoAditivo",
						 colunaDataPagamento = super.getNomeTabela() + ".dataPagamento",
						 colunaContrato = super.getNomeTabela() + ".contratoNumero",
						 colunaSei = super.getNomeTabela() + ".numeroSei";

	public ProcessoDAO() {
		super("processo");
	}
	
	public ArrayList<Processo> getByContrato(int numeroContrato){
		iniciaConexaoComBanco();
		
		super.setSqlQuery(
			"select * from " + super.getNomeTabela() + " where " + colunaContrato + " = ?"
		);
		
		try{
			super.setStatement(
				super.getDbConnection().prepareStatement(
					super.getSqlQuery()
				)
			);
			
			super.getStatement().setInt(
				1,
				numeroContrato
			);
			
			super.setResultado(
				super.getStatement().executeQuery()
			);
		} catch (SQLException e){
			e.printStackTrace();
			encerraConexaocomBanco();
			return null;
		}
		ArrayList<Processo> lista = new ArrayList<Processo>();
		Processo p;
				
		try {
			while(super.getResultado().next()){
				p = new Processo();
				
				p.setNotaFiscal(
					super.getResultado().getString(colunaNotaFiscal)
				);
					
				p.setAditivo(
					super.getResultado().getBigDecimal(colunaAditivo)
				);
					
				p.setTipoAditivo(
					super.getResultado().getString(colunaTipoAditivo)
				);

				p.setDataPagamento(
					super.getResultado().getDate(colunaDataPagamento)
				);
				
				p.setNumeroSei(
					super.getResultado().getString(colunaSei)
				);
				
				lista.add(p);
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
