package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Processo;

public class ProcessoDAO extends DAO{
	private final String colunaNotaFiscal = getNomeTabela() + ".notaFiscal",
						 colunaAditivo = getNomeTabela() + ".aditivo",
						 colunaTipoAditivo = getNomeTabela() + ".tipoAditivo",
						 colunaDataPagamento = getNomeTabela() + ".dataPagamento",
						 colunaContrato = getNomeTabela() + ".contratoNumero",
						 colunaSei = getNomeTabela() + ".numeroSei";

	public ProcessoDAO() {
		super("processo");
	}
	
	public ArrayList<Processo> getByContrato(int numeroContrato){
		iniciaConexaoComBanco();
		
		setSqlQuery(
			"select * from " + getNomeTabela() + " where " + colunaContrato + " = ?"
		);
		
		try{
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
			getStatement().setInt(
				1,
				numeroContrato
			);
			
			setResultado(
				getStatement().executeQuery()
			);
		} catch (SQLException e){
			e.printStackTrace();
			encerraConexaocomBanco();
			return null;
		}
		ArrayList<Processo> lista = new ArrayList<Processo>();
		Processo p;
				
		try {
			while(getResultado().next()){
				p = new Processo();
				
				p.setNotaFiscal(
					getResultado().getString(colunaNotaFiscal)
				);
					
				p.setAditivo(
					getResultado().getBigDecimal(colunaAditivo)
				);
					
				p.setTipoAditivo(
					getResultado().getString(colunaTipoAditivo)
				);

				p.setDataPagamento(
					getResultado().getDate(colunaDataPagamento)
				);
				
				p.setNumeroSei(
					getResultado().getString(colunaSei)
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
