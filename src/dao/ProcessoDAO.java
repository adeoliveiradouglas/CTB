package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
						 colunaReferencia = getNomeTabela() + ".referencia",
						 colunaUsuario = getNomeTabela() + ".usuario_id",
						 colunaIdProcesso = getNomeTabela() + ".idProcesso",
						 ordernarPorDataReferencia = " order by " + colunaReferencia,
						 ordernarPorId = " order by " + colunaIdProcesso;
	public ProcessoDAO() {
		super("processo");
	}
	
	public ProcessoDAO(Connection conexao) {
		super("processo", conexao);
	}
	
	public ArrayList<Processo> getByContrato(int id){
		iniciaConexaoComBanco();
		
		setSqlQuery(
			"select * from " + getNomeTabela() + " where " + colunaContrato + " = ?" + ordernarPorId + " desc"
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
					getResultado().getInt(colunaIdProcesso),
					getResultado().getString(colunaNotaFiscal),
					getResultado().getString(colunaTipoAditivo),
					getResultado().getString(colunaSei),
					getResultado().getBigDecimal(colunaAditivo),
					getResultado().getBigDecimal(colunaValor),
					getResultado().getDate(colunaDataPagamento),
					getResultado().getDate(colunaDataProcesso),
					getResultado().getDate(colunaReferencia),
					id,
					new UsuarioDAO().getById(getResultado().getInt(colunaUsuario))
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
		//o mes de referencia do processo deve estar no formato de numero
		iniciaConexaoComBanco();
		
		setSqlQuery("insert into " + getNomeTabela() + " (" +
				colunaNotaFiscal + ", " +
				colunaAditivo + ", " +
				colunaValor + ", " +
				colunaTipoAditivo + ", " +
				colunaDataProcesso + ", " +
				colunaSei + ", " +
				colunaContrato + ", " +
				colunaReferencia +
				") values (?, ?, ?, ?, ?, ?, ?, ?)"
		);
		
		try{
			int posicao = 0;
			
			String d = "01/" + processo.getMesAsInt() + "/" + processo.getAno();
			java.util.Date referencia = new SimpleDateFormat("yyyy-MM-dd").parse(d);
			
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
			getStatement().setString(
				++posicao, 
				processo.getNotaFiscal()
			);
			
			getStatement().setBigDecimal(
				++posicao, 
				processo.getAditivo()
			);
			
			getStatement().setBigDecimal(
				++posicao, 
				processo.getValor()
			);
			
			getStatement().setString(
				++posicao, 
				processo.getTipoAditivo()
			);
			
			getStatement().setDate(
				++posicao, 
				new Date(processo.getDataProcesso().toDate().getTime())
			);
			
			getStatement().setString(
				++posicao, 
				processo.getNumeroSei()
			);
			
			getStatement().setInt(
				++posicao, 
				processo.getIdContrato()
			);
			
			getStatement().setDate(
				++posicao, 
				new Date(referencia.getTime())
			);
			
			getStatement().executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		encerraConexaocomBanco();
	}

	public ArrayList<Processo> getAllSemPagamento() {
		iniciaConexaoComBanco();
		
		setSqlQuery(
			"select * from " + getNomeTabela() + " where " + colunaDataPagamento + " is null" + ordernarPorId + " desc"
		);
		
		try{
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
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
					getResultado().getInt(colunaIdProcesso),
					getResultado().getString(colunaNotaFiscal),
					getResultado().getString(colunaTipoAditivo),
					getResultado().getString(colunaSei),
					getResultado().getBigDecimal(colunaAditivo),
					getResultado().getBigDecimal(colunaValor),
					getResultado().getDate(colunaDataPagamento),
					getResultado().getDate(colunaDataProcesso),
					getResultado().getDate(colunaReferencia),
					getResultado().getInt(colunaContrato),
					new UsuarioDAO().getById(getResultado().getInt(colunaUsuario))					
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

	public void atualizarPagamento(String numeroSei, int idTesoureiro) {
		iniciaConexaoComBanco();
		
		setSqlQuery("update "+getNomeTabela()+" set "+colunaDataPagamento+ " = NOW(), " +colunaUsuario+ " = ? where " +colunaIdProcesso+ " = ?");
		
		try{
			setStatement(getDbConnection().prepareStatement(getSqlQuery()));
			
			getStatement().setInt(1, idTesoureiro);
			getStatement().setString(2, numeroSei);
			
			getStatement().executeUpdate();
		}catch(SQLException e){
			System.out.println(e);
		}
		encerraConexaocomBanco();
	}

	public ArrayList<Processo> getByContratoSemPagamento(int idContrato) {
		iniciaConexaoComBanco();

		setSqlQuery(
			"select * from " + getNomeTabela() + " where " + colunaDataPagamento + " is null and " + 
			colunaContrato + " = ?" + ordernarPorDataReferencia + " desc"
		);

		ArrayList<Processo> lista = new ArrayList<Processo>();
		Processo p;
		
		try{
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
			getStatement().setInt(
				1, 
				idContrato
			);
			
			setResultado(
				getStatement().executeQuery()
			);
			
			while(getResultado().next()){
				p = new Processo(
					getResultado().getInt(colunaIdProcesso),
					getResultado().getString(colunaNotaFiscal),
					getResultado().getString(colunaTipoAditivo),
					getResultado().getString(colunaSei),
					getResultado().getBigDecimal(colunaAditivo),
					getResultado().getBigDecimal(colunaValor),
					getResultado().getDate(colunaDataPagamento),
					getResultado().getDate(colunaDataProcesso),
					getResultado().getDate(colunaReferencia),
					getResultado().getInt(colunaContrato),
					new UsuarioDAO().getById(getResultado().getInt(colunaUsuario))
				);
				
				lista.add(p);
			}
		} catch (SQLException e){
			System.out.println(e);
			encerraConexaocomBanco();
			return new ArrayList<Processo>();
		}
		
		encerraConexaocomBanco();
		return lista;
	}
}
