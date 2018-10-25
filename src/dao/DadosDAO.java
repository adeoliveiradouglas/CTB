package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import entity.Dados;

public class DadosDAO extends DAO{
	private final String colunaNotaFiscal = getNomeTabela() + ".notaFiscal",
						 colunaAditivo = getNomeTabela() + ".aditivo",
						 colunaValor = getNomeTabela() + ".valor",
						 colunaTipoAditivo = getNomeTabela() + ".tipoAditivo",
						 colunaDataPagamento = getNomeTabela() + ".dataPagamento",
						 colunaData = getNomeTabela() + ".data", 
						 colunaContrato = getNomeTabela() + ".contrato_id",
						 colunaSei = getNomeTabela() + ".numeroSei",
						 colunaReferencia = getNomeTabela() + ".referencia",
						 colunaTesoureiro = getNomeTabela() + ".tesoureiro_id",
						 colunaId = getNomeTabela() + ".id",
						 colunaSaldo = getNomeTabela() + ".saldo",
						 ordernarPorDataReferencia = " order by " + colunaReferencia;
	
	public DadosDAO() {
		super("dados");
	}
	
	public DadosDAO(Connection conexao) {
		super("dados", conexao);
	}
	
	public List<Dados> getByContrato(int id){
		iniciaConexaoComBanco();
		
		setSqlQuery(
			"select * from " + getNomeTabela() + " where " + colunaContrato + " = ?" + ordernarPorDataReferencia + " desc"
		);
		
		List<Dados> lista = new ArrayList<Dados>();
		Dados p;
				
		try {
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
			
//			quantidade de itens no resultSet
			int item = getResultSize();			
			
			while(getResultado().next()){
				
				p = new Dados(
					getResultado().getInt(colunaId),
					item--,
					getResultado().getString(colunaNotaFiscal),
					getResultado().getString(colunaTipoAditivo),
					getResultado().getString(colunaSei),
					getResultado().getBigDecimal(colunaAditivo),
					getResultado().getBigDecimal(colunaValor),
					getResultado().getBigDecimal(colunaSaldo),
					getResultado().getDate(colunaDataPagamento),
					getResultado().getDate(colunaData),
					getResultado().getDate(colunaReferencia),
					id,
					new UsuarioDAO(getDbConnection()).getById(getResultado().getInt(colunaTesoureiro))
				);
				
				lista.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			lista = new ArrayList<Dados>();
		}
		
		encerraConexaocomBanco();
		return lista;
	}
	
	public void inserir(Dados dado){
		//o mes de referencia do dado deve estar no formato de numero
		iniciaConexaoComBanco();
		
		setSqlQuery("insert into " + getNomeTabela() + " (" +
				colunaNotaFiscal + ", " +
				colunaAditivo + ", " +
				colunaValor + ", " +
				colunaSaldo + ", " +
				colunaTipoAditivo + ", " +
				colunaData + ", " +
				colunaSei + ", " +
				colunaContrato + ", " +
				colunaReferencia +
				") values (?, ?, ?, ?, ?, ?, ?, ?, ?)"
		);
		
		try{
			int posicao = 0;
			
			String d = dado.getAno() + "-" + dado.getMesAsInt() + "-01" ;
			java.util.Date referencia = new SimpleDateFormat("yyyy-MM-dd").parse(d);
			
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
			getStatement().setString(
				++posicao, 
				dado.getNotaFiscal()
			);
			
			getStatement().setBigDecimal(
				++posicao, 
				dado.getAditivo()
			);
			
			getStatement().setBigDecimal(
				++posicao, 
				dado.getValor()
			);
			
			getStatement().setBigDecimal(
				++posicao, 
				dado.getSaldo()
			);
			
			getStatement().setString(
				++posicao, 
				dado.getTipoAditivo()
			);
			
			getStatement().setDate(
				++posicao, 
				new Date(dado.getData().toDate().getTime())
			);
			
			getStatement().setString(
				++posicao, 
				dado.getNumeroSei()
			);
			
			getStatement().setInt(
				++posicao, 
				dado.getIdContrato()
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

	public void atualizarPagamento(int idDados, int idTesoureiro, DateTime dataPagamento) {
		iniciaConexaoComBanco();
		
		setSqlQuery("update "+getNomeTabela()+" set "+colunaDataPagamento+ " = ?, " +colunaTesoureiro+ " = ? where " +colunaId+ " = ?");
		
		try{
			setStatement(getDbConnection().prepareStatement(getSqlQuery()));
			
			int i = 1;
			
			getStatement().setDate(
				i++, 
				new Date(dataPagamento.toDate().getTime())
			);
			
			getStatement().setInt(
				i++, 
				idTesoureiro
			);
			getStatement().setInt(
				i++, 
				idDados
			);
			
			getStatement().executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		encerraConexaocomBanco();
	}
	
	public void atualizarPagamento(int idDados, int idTesoureiro) {	
		DateTime hoje = new DateTime();
		
		atualizarPagamento(idDados, idTesoureiro, hoje);
	}

	public List<Dados> getByContratoSemPagamento(int idContrato) {
		iniciaConexaoComBanco();

		setSqlQuery(
			"select * from " + getNomeTabela() + " where " + colunaDataPagamento + " is null and " + 
			colunaContrato + " = ?" + ordernarPorDataReferencia + " desc"
		);

		List<Dados> lista = new ArrayList<Dados>();
		Dados p;
		
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
			
			int item = getResultSize();
			
			while(getResultado().next()){
				p = new Dados(
					getResultado().getInt(colunaId),
					item--,
					getResultado().getString(colunaNotaFiscal),
					getResultado().getString(colunaTipoAditivo),
					getResultado().getString(colunaSei),
					getResultado().getBigDecimal(colunaAditivo),
					getResultado().getBigDecimal(colunaValor),
					getResultado().getBigDecimal(colunaSaldo),
					getResultado().getDate(colunaDataPagamento),
					getResultado().getDate(colunaData),
					getResultado().getDate(colunaReferencia),
					getResultado().getInt(colunaContrato),
					new UsuarioDAO(getDbConnection()).getById(getResultado().getInt(colunaTesoureiro))
				);
				
				lista.add(p);
			}
		} catch (SQLException e){
			e.printStackTrace();
			lista = new ArrayList<Dados>();
		}
		
		encerraConexaocomBanco();
		return lista;
	}

	public List<Dados> getAllSemPagamento() {
		iniciaConexaoComBanco();
		
		setSqlQuery(
			"select * from " + getNomeTabela() + " where " + colunaDataPagamento + " is null" + ordernarPorDataReferencia + " desc"
		);
		

		List<Dados> lista = new ArrayList<Dados>();
		Dados p;
		
		try{
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
			setResultado(
				getStatement().executeQuery()
			);
			
			int item = getResultSize();
			
			while(getResultado().next()){
				p = new Dados(
					getResultado().getInt(colunaId),
					item--,
					getResultado().getString(colunaNotaFiscal),
					getResultado().getString(colunaTipoAditivo),
					getResultado().getString(colunaSei),
					getResultado().getBigDecimal(colunaAditivo),
					getResultado().getBigDecimal(colunaValor),
					getResultado().getBigDecimal(colunaSaldo),
					getResultado().getDate(colunaDataPagamento),
					getResultado().getDate(colunaData),
					getResultado().getDate(colunaReferencia),
					getResultado().getInt(colunaContrato),
					new UsuarioDAO(getDbConnection()).getById(getResultado().getInt(colunaTesoureiro))					
				);
				
				lista.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			lista = new ArrayList<Dados>();
		}
		
		encerraConexaocomBanco();
		return lista;
	}
}