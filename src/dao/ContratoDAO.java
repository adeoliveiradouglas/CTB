package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Contrato;

public class ContratoDAO extends DAO{
	private final String colunaNumero = getNomeTabela() + ".numero",
			 			 colunaNome = getNomeTabela() + ".nome",
			 			 colunaEmpresaCnpj = getNomeTabela() + ".cnpjEmpresaContratada",
			 			 colunaEmpresaNome = getNomeTabela() + ".nomeEmpresaContratada",
			 			 colunaObjeto = getNomeTabela() + ".objeto",
			 			 colunaPortaria = getNomeTabela() + ".portaria",
			 			 colunaDataAssinatura = getNomeTabela() + ".dataAssinatura",
			 			 colunaDataOrdemServico = getNomeTabela() + ".dataOrdemServico",
			 			 colunaDataGarantia = getNomeTabela() + ".dataGarantia",
			 			 colunaDataVencimentoContrato = getNomeTabela() + ".dataVencimentoContrato",
			 			 colunaDataVencimentoGarantia = getNomeTabela() + ".dataVencimentoGarantia",
					 	 colunaValorInicial = getNomeTabela() + ".valorInicial",
					 	 colunaValorTotal = getNomeTabela() + ".valorTotal",
					 	 colunaValorAditivos = getNomeTabela() + ".valorAditivos",
					 	 colunaGestor = getNomeTabela() + ".gestor",
					 	 colunaFiscal = getNomeTabela() + ".fiscal",
					 	 colunaRecurso = getNomeTabela() + ".recurso_id",
					 	 colunaFontePagante = getNomeTabela() + ".fontePagante_id",
					 	 colunaUso = getNomeTabela() + ".uso_id";
	
	
	public ContratoDAO() {
		super("contrato");
	}
	
	public ArrayList<Contrato> getByGestor(int matricula){
		iniciaConexaoComBanco();
		
//		Exemplo: select * from contrato where gestor = matricula
		setSqlQuery(
			"select * from " + getNomeTabela() + " where " + colunaGestor + " = ?"
		);
		
		try {
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
			getStatement().setInt(
				1, 
				matricula
			);
			
			setResultado(
				getStatement().executeQuery()
			);
		} catch (SQLException e) {
			e.printStackTrace();
			encerraConexaocomBanco();
			return null;
		}

		ArrayList<Contrato> lista = new ArrayList<Contrato>();
		Contrato c = null;
		
		try {
			while (getResultado().next()){
				/*c = new Contrato(
					getResultado().getInt(colunaNumero),
					getResultado().getInt(colunaPortaria),
					getResultado().getInt(colunaGestor),
					getResultado().getInt(colunaFiscal),
					getResultado().getString(colunaNome),
					getResultado().getString(colunaEmpresaCnpj),	
					getResultado().getString(colunaEmpresaNome),
					getResultado().getString(colunaObjeto),
					new OutroDAO(tabelaRecurso).getById(
						getResultado().getInt(colunaRecurso)
					).getNome(),
					new OutroDAO(tabelaFontePagante).getById(
						getResultado().getInt(colunaFontePagante)
					).getNome(),
					new OutroDAO(tabelaUso).getById(
						getResultado().getInt(colunaUso)
					).getNome(),
					getResultado().getDate(colunaDataAssinatura),
					getResultado().getDate(colunaDataOrdemServico),
					getResultado().getDate(colunaDataGarantia),
					getResultado().getDate(colunaDataVencimentoContrato),
					getResultado().getDate(colunaDataVencimentoGarantia),
					getResultado().getBigDecimal(colunaValorInicial)
				);
				
				lista.add(c);*/
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

	public void inserir(Contrato c) {
		iniciaConexaoComBanco();
		
		setSqlQuery(
			"insert into " + getNomeTabela() + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
		);
		
		try{
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
			int posicao = 1;
			
			getStatement().setInt(
				posicao, 
				c.getNumero()
			);
			
			getStatement().setString(
				++posicao, 
				c.getNome()
			);
			
			getStatement().setString(
				++posicao, 
				c.getCnpjEmpresaContratada()
			);
			
			getStatement().setString(
				++posicao, 
				c.getNomeEmpresaContratada()
			);

			getStatement().setString(
				++posicao, 
				c.getObjeto()
			);
			
			getStatement().setInt(
				++posicao, 
				c.getPortaria()
			);
			
			getStatement().setDate(
				++posicao, 
				new Date(c.getDataAssinatura().getTime())
			);
			
			getStatement().setDate(
				++posicao, 
				new Date(c.getDataOrdemServico().getTime())
			);
			
			getStatement().setDate(
				++posicao, 
				new Date(c.getDataGarantia().getTime())
			);
			
			getStatement().setDate(
				++posicao, 
				new Date(c.getDataVencimentoContrato().getTime())
			);
			
			getStatement().setDate(
				++posicao, 
				new Date(c.getDataVencimentoGarantia().getTime())
			);
			
			getStatement().setBigDecimal(
				++posicao, 
				c.getValorInicial()
			);
			
			getStatement().setBigDecimal(
				++posicao, 
				c.getValorTotal()
			);
			
			getStatement().setBigDecimal(
				++posicao, 
				c.getValorAditivos()
			);
						
			getStatement().setInt(
				++posicao, 
				c.getGestor()
			);
			
			getStatement().setInt(
				++posicao, 
				c.getFiscal()
			);
			
			getStatement().setInt(
				++posicao, 
				c.getRecurso().getId()
			);
			
			getStatement().setInt(
				++posicao, 
				c.getFontePagante().getId()
			);
			
			getStatement().setInt(
				++posicao, 
				c.getUso().getId()
			);
			
			getStatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			encerraConexaocomBanco();
		}
	}
}
