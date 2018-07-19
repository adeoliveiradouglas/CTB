package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Processo;

public class ProcessoDAO extends DAO{
	private final String colunaNotaFiscal = getNomeTabela() + ".notaFiscal",
						 colunaAditivo = getNomeTabela() + ".aditivo",
						 colunaValor = getNomeTabela() + ".valor",
						 colunaTipoAditivo = getNomeTabela() + ".tipoAditivo",
						 colunaDataPagamento = getNomeTabela() + ".dataPagamento",
						 colunaDataProcesso = getNomeTabela() + ".dataProcesso", 
						 colunaContrato = getNomeTabela() + ".contrato_id",
						 colunaSei = getNomeTabela() + ".numeroSei",
						 colunaAno = getNomeTabela() + ".ano",
						 colunaMes = getNomeTabela() + ".mes";
						 

	public ProcessoDAO() {
		super("processo");
	}
	
	public ArrayList<Processo> getByContrato(int id){
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
				id
			);
			
			setResultado(
				getStatement().executeQuery()
			);
		} catch (SQLException e){
			System.out.println(e);;
			encerraConexaocomBanco();
			return new ArrayList<Processo>();
		}
		
		ArrayList<Processo> lista = new ArrayList<Processo>();
		Processo p;
				
		try {
			while(getResultado().next()){
				p = new Processo(
					getResultado().getString(colunaNotaFiscal),
					getResultado().getString(colunaTipoAditivo),
					getResultado().getString(colunaSei),
					getResultado().getString(colunaAno),
					getResultado().getString(colunaMes),
					getResultado().getBigDecimal(colunaAditivo),
					getResultado().getBigDecimal(colunaValor),
					getResultado().getDate(colunaDataPagamento),
					getResultado().getDate(colunaDataProcesso),
					id
				);
				
				lista.add(p);
			}
		} catch (SQLException e) {
			System.out.println(e);;
			encerraConexaocomBanco();
			return new ArrayList<Processo>();
		}
		
		encerraConexaocomBanco();
		return lista;
	}
	
	public void inserir(Processo processo){
		iniciaConexaoComBanco();
		
//		setSqlQuery();
		encerraConexaocomBanco();
	}
}
