package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Contrato;

public class ContratoDAO extends DAO {
	private final String colunaNumero = getNomeTabela() + ".numero", 
			colunaId = getNomeTabela() + ".idContrato",
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
			colunaGestor = getNomeTabela() + ".gestor_id", 
			colunaFiscal = getNomeTabela() + ".fiscal_id",
			colunaRecurso = getNomeTabela() + ".recurso_id", 
			colunaFontePagante = getNomeTabela() + ".fontePagante_id",
			colunaUso = getNomeTabela() + ".uso_id",
			coluna90 = getNomeTabela() + ".avisado90",
			coluna60 =getNomeTabela() + ".avisado60",
			coluna45 = getNomeTabela() + ".avisado45";

	public ContratoDAO() {
		super("contrato");
	}

	public ArrayList<Contrato> getByGestor(int id) {
		return getByGestor(id, colunaId);
	}

	public ArrayList<Contrato> getByGestor(int id, String ordenacao) {
		ArrayList<Contrato> lista = new ArrayList<Contrato>();
		
		iniciaConexaoComBanco();

		// Exemplo: select * from contrato where gestor = matricula
		setSqlQuery("select * from " + getNomeTabela() + " where " + colunaGestor + " = ? order by " + ordenacao + " desc");

		try {
			setStatement(getDbConnection().prepareStatement(getSqlQuery()));

			getStatement().setInt(1, id);

			setResultado(getStatement().executeQuery());
			Contrato c = null;

			// Traduzir resultado para objeto
			while (getResultado().next()) {
				c = new Contrato(
					getResultado().getInt(colunaId), 
					getResultado().getInt(colunaPortaria),
					new UsuarioDAO(getDbConnection()).getById(getResultado().getInt(colunaGestor)),
					new UsuarioDAO(getDbConnection()).getById(getResultado().getInt(colunaFiscal)),
					new OutroDAO("recurso", getDbConnection()).getById(getResultado().getInt(colunaRecurso)),
					new OutroDAO("fontepagante", getDbConnection()).getById(getResultado().getInt(colunaFontePagante)),
					new OutroDAO("uso", getDbConnection()).getById(getResultado().getInt(colunaUso)),
					getResultado().getString(colunaNumero),
					getResultado().getString(colunaEmpresaCnpj), 
					getResultado().getString(colunaEmpresaNome),
					getResultado().getString(colunaObjeto),
					getResultado().getDate(colunaDataAssinatura), 
					getResultado().getDate(colunaDataOrdemServico),
					getResultado().getDate(colunaDataGarantia),
					getResultado().getDate(colunaDataVencimentoContrato),
					getResultado().getDate(colunaDataVencimentoGarantia),
					getResultado().getBigDecimal(colunaValorInicial),
					new ProcessoDAO(getDbConnection()).getByContrato(getResultado().getInt(colunaId)),
					getResultado().getBoolean(coluna90),
					getResultado().getBoolean(coluna60),
					getResultado().getBoolean(coluna45)
				);

				lista.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			lista = new ArrayList<Contrato>();
		}

		encerraConexaocomBanco();
		return lista;
	}

	public ArrayList<Contrato> getAllRecente(int quantidade) {
		/*
		 * Retorna uma lista com a quantidade dos contratos mais recentes
		 * Exemplo: parâmetro quantidade = 5, retorna os 5 mais recentes.
		 * 
		 * Se houver qualquer erro no procedimento, retorna uma lista vazia.
		 */

		iniciaConexaoComBanco();

		setSqlQuery("select * from " + getNomeTabela() + " order by " + colunaId + " desc");

		ArrayList<Contrato> recentes = new ArrayList<Contrato>();

		try {
			// monta o statement
			setStatement(
					// pega prepareStatement da conexao
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);

			setResultado(getStatement().executeQuery());
			
			Contrato c;
			while (getResultado().next() && quantidade > 0) {
				/*
				 * Enquanto houverem elementos do resulta de query e estiver
				 * dentro no parâmetro, continua processando
				 */
				c = new Contrato(
					getResultado().getInt(colunaId), 
					getResultado().getInt(colunaPortaria),
					new UsuarioDAO(getDbConnection()).getById(getResultado().getInt(colunaGestor)),
					new UsuarioDAO(getDbConnection()).getById(getResultado().getInt(colunaFiscal)),
					new OutroDAO("recurso", getDbConnection()).getById(getResultado().getInt(colunaRecurso)),
					new OutroDAO("fontepagante", getDbConnection()).getById(getResultado().getInt(colunaFontePagante)),
					new OutroDAO("uso", getDbConnection()).getById(getResultado().getInt(colunaUso)),
					getResultado().getString(colunaNumero),
					getResultado().getString(colunaEmpresaCnpj), 
					getResultado().getString(colunaEmpresaNome),
					getResultado().getString(colunaObjeto),
					getResultado().getDate(colunaDataAssinatura), 
					getResultado().getDate(colunaDataOrdemServico),
					getResultado().getDate(colunaDataGarantia),
					getResultado().getDate(colunaDataVencimentoContrato),
					getResultado().getDate(colunaDataVencimentoGarantia),
					getResultado().getBigDecimal(colunaValorInicial),
					new ProcessoDAO(getDbConnection()).getByContrato(getResultado().getInt(colunaId)),
					getResultado().getBoolean(coluna90),
					getResultado().getBoolean(coluna60),
					getResultado().getBoolean(coluna45)
				);

				// Adiciona na lista
				recentes.add(c);

				// Diminui a quantidade restante para processar
				--quantidade;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			recentes = new ArrayList<Contrato>();
		}

		encerraConexaocomBanco();
		return recentes;
	}

	public ArrayList<Contrato> getVencimento90() {
		ArrayList<Contrato> recentes = new ArrayList<Contrato>();

		return recentes;
	}

	public void inserir(Contrato c) {
		iniciaConexaoComBanco();

		setSqlQuery("insert into " + getNomeTabela() + " (" + colunaNumero + ", " + colunaEmpresaCnpj + ", "
				+ colunaEmpresaNome + ", " + colunaObjeto + ", " + colunaPortaria + ", " + colunaDataAssinatura + ", "
				+ colunaDataOrdemServico + ", " + colunaDataGarantia + ", " + colunaDataVencimentoContrato + ", "
				+ colunaDataVencimentoGarantia + ", " + colunaValorInicial + ", " + colunaGestor + ", " + colunaFiscal
				+ ", " + colunaRecurso + ", " + colunaFontePagante + ", " + colunaUso
				+ ") values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		try {
			setStatement(getDbConnection().prepareStatement(getSqlQuery()));

			int posicao = 1;

			getStatement().setString(posicao, c.getNumero());

			getStatement().setString(++posicao, c.getCnpjEmpresaContratada());

			getStatement().setString(++posicao, c.getNomeEmpresaContratada());

			getStatement().setString(++posicao, c.getObjeto());

			getStatement().setInt(++posicao, c.getPortaria());

			getStatement().setDate(++posicao, new Date(c.getDataAssinatura().toDate().getTime()));

			getStatement().setDate(++posicao, new Date(c.getDataOrdemServico().toDate().getTime()));

			getStatement().setDate(++posicao, new Date(c.getDataGarantia().toDate().getTime()));

			getStatement().setDate(++posicao, new Date(c.getDataVencimentoContrato().toDate().getTime()));

			getStatement().setDate(++posicao, new Date(c.getDataVencimentoGarantia().toDate().getTime()));

			getStatement().setBigDecimal(++posicao, c.getValorInicial());

			getStatement().setInt(++posicao, c.getGestor().getId());

			getStatement().setInt(++posicao, c.getFiscal().getId());

			getStatement().setInt(++posicao, c.getRecurso().getId());

			getStatement().setInt(++posicao, c.getFontePagante().getId());

			getStatement().setInt(++posicao, c.getUso().getId());

			getStatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		encerraConexaocomBanco();
	}

	public ArrayList<Contrato> getAll() {
		/*
		 * Usado quando não necessita de resultado ordenado
		 */
		return getAll(colunaId);
	}

	public ArrayList<Contrato> getAll(String ordenacao) {
		ArrayList<Contrato> contratos = new ArrayList<Contrato>();

		iniciaConexaoComBanco();

		setSqlQuery("select * from " + getNomeTabela() + " order by " + ordenacao);

		try {
			// monta o statement
			setStatement(
				// pega prepareStatement da conexao
				getDbConnection().prepareStatement(getSqlQuery())
			);

			setResultado(getStatement().executeQuery());
			
			Contrato c;
			while (getResultado().next()) {
				/*
				 * Enquanto houverem elementos do resulta de quary e estiver
				 * dentro no parâmetro, continua processando
				 */
				c = new Contrato(
					getResultado().getInt(colunaId), 
					getResultado().getInt(colunaPortaria),
					new UsuarioDAO(getDbConnection()).getById(getResultado().getInt(colunaGestor)),
					new UsuarioDAO(getDbConnection()).getById(getResultado().getInt(colunaFiscal)),
					new OutroDAO("recurso", getDbConnection()).getById(getResultado().getInt(colunaRecurso)),
					new OutroDAO("fontepagante", getDbConnection()).getById(getResultado().getInt(colunaFontePagante)),
					new OutroDAO("uso", getDbConnection()).getById(getResultado().getInt(colunaUso)),
					getResultado().getString(colunaNumero),
					getResultado().getString(colunaEmpresaCnpj), 
					getResultado().getString(colunaEmpresaNome),
					getResultado().getString(colunaObjeto),
					getResultado().getDate(colunaDataAssinatura), 
					getResultado().getDate(colunaDataOrdemServico),
					getResultado().getDate(colunaDataGarantia),
					getResultado().getDate(colunaDataVencimentoContrato),
					getResultado().getDate(colunaDataVencimentoGarantia),
					getResultado().getBigDecimal(colunaValorInicial),
					new ProcessoDAO(getDbConnection()).getByContrato(getResultado().getInt(colunaId)),
					getResultado().getBoolean(coluna90),
					getResultado().getBoolean(coluna60),
					getResultado().getBoolean(coluna45)
				);

				// Adiciona na lista
				contratos.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			contratos = new ArrayList<Contrato>();
		}

		encerraConexaocomBanco();
		return contratos;
	}

	public ArrayList<Contrato> getByFiscal(int id) {
		/*
		 * Usado quando não necessita de resultado ordenado
		 */
		return getByFiscal(id, "");
	}

	public ArrayList<Contrato> getByFiscal(int id, String ordenacao) {
		/*
		 * Retorna a lista com a ordenacao desejada
		 */
		ArrayList<Contrato> lista = new ArrayList<Contrato>();
		Contrato c = null;

		iniciaConexaoComBanco();

		// Exemplo: select * from contrato where gestor = matricula
		setSqlQuery("select * from " + getNomeTabela() + " where " + colunaFiscal + " = ? order by " + ordenacao);

		try {
			setStatement(getDbConnection().prepareStatement(getSqlQuery()));

			getStatement().setInt(1, id);

			setResultado(getStatement().executeQuery());

			// Traduzir resultado para objeto
			while (getResultado().next()) {
				c = new Contrato(
					getResultado().getInt(colunaId), 
					getResultado().getInt(colunaPortaria),
					new UsuarioDAO(getDbConnection()).getById(getResultado().getInt(colunaGestor)),
					new UsuarioDAO(getDbConnection()).getById(getResultado().getInt(colunaFiscal)),
					new OutroDAO("recurso", getDbConnection()).getById(getResultado().getInt(colunaRecurso)),
					new OutroDAO("fontepagante", getDbConnection()).getById(getResultado().getInt(colunaFontePagante)),
					new OutroDAO("uso", getDbConnection()).getById(getResultado().getInt(colunaUso)),
					getResultado().getString(colunaNumero),
					getResultado().getString(colunaEmpresaCnpj), 
					getResultado().getString(colunaEmpresaNome),
					getResultado().getString(colunaObjeto),
					getResultado().getDate(colunaDataAssinatura), 
					getResultado().getDate(colunaDataOrdemServico),
					getResultado().getDate(colunaDataGarantia),
					getResultado().getDate(colunaDataVencimentoContrato),
					getResultado().getDate(colunaDataVencimentoGarantia),
					getResultado().getBigDecimal(colunaValorInicial),
					new ProcessoDAO(getDbConnection()).getByContrato(getResultado().getInt(colunaId)),
					getResultado().getBoolean(coluna90),
					getResultado().getBoolean(coluna60),
					getResultado().getBoolean(coluna45)
				);

				lista.add(c);
			}
		} catch (SQLException e) {
			System.out.println(e);
			;
			encerraConexaocomBanco();
			return null;
		}

		encerraConexaocomBanco();
		return lista;
	}

	public Contrato getByIdSemPagamento(int id) {
		iniciaConexaoComBanco();

		setSqlQuery("select * from " + getNomeTabela() + " where " + colunaId + " = ?");

		Contrato c = null;

		try {
			// monta o statement
			setStatement(
					// pega prepareStatement da conexao
					getDbConnection().prepareStatement(getSqlQuery()));

			getStatement().setInt(1, id);

			setResultado(getStatement().executeQuery());

			if (getResultado().next()) {
				/*
				 * Enquanto houverem elementos do resulta de quary e estiver
				 * dentro no parâmetro, continua processando
				 */
				c = new Contrato(
					getResultado().getInt(colunaId), 
					getResultado().getInt(colunaPortaria),
					new UsuarioDAO(getDbConnection()).getById(getResultado().getInt(colunaGestor)),
					new UsuarioDAO(getDbConnection()).getById(getResultado().getInt(colunaFiscal)),
					new OutroDAO("recurso", getDbConnection()).getById(getResultado().getInt(colunaRecurso)),
					new OutroDAO("fontepagante", getDbConnection()).getById(getResultado().getInt(colunaFontePagante)),
					new OutroDAO("uso", getDbConnection()).getById(getResultado().getInt(colunaUso)),
					getResultado().getString(colunaNumero),
					getResultado().getString(colunaEmpresaCnpj), 
					getResultado().getString(colunaEmpresaNome),
					getResultado().getString(colunaObjeto),
					getResultado().getDate(colunaDataAssinatura), 
					getResultado().getDate(colunaDataOrdemServico),
					getResultado().getDate(colunaDataGarantia),
					getResultado().getDate(colunaDataVencimentoContrato),
					getResultado().getDate(colunaDataVencimentoGarantia),
					getResultado().getBigDecimal(colunaValorInicial),
					new ProcessoDAO(getDbConnection()).getByContratoSemPagamento(id),
					getResultado().getBoolean(coluna90),
					getResultado().getBoolean(coluna60),
					getResultado().getBoolean(coluna45)
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			c = null;
		}

		encerraConexaocomBanco();
		return c;
	}

	public void atualizarDataVencimento(int idContrato, java.util.Date novaDataVencimento) {
		iniciaConexaoComBanco();
		
		setSqlQuery("update " + getNomeTabela() + " set " + colunaDataVencimentoContrato + " = ? where " + colunaId
				+ " = ?");

		try {
			setStatement(getDbConnection().prepareStatement(getSqlQuery()));
			
			getStatement().setDate(1, new Date(novaDataVencimento.getTime()));
			getStatement().setInt(2, idContrato);
			
			getStatement().executeUpdate();
			
			atualizarAvisoDeVencimento(new Contrato(idContrato, false, false, false));
		} catch (SQLException e) {}

		encerraConexaocomBanco();
	}

	public void atualizarAvisoDeVencimento(Contrato c){
		iniciaConexaoComBanco();
		
		setSqlQuery("update " + getNomeTabela() + " set " + coluna45 + " = ?, " + coluna60 + " = ?, "
				+ coluna90 + " = ? where " + colunaId + " = ?");

		try {
			setStatement(getDbConnection().prepareStatement(getSqlQuery()));
			int posicao = 1;
			
			getStatement().setBoolean(posicao, c.isAvisado45());
			getStatement().setBoolean(++posicao, c.isAvisado60());
			getStatement().setBoolean(++posicao, c.isAvisado90());
			getStatement().setInt(++posicao, c.getId());
			
			getStatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}

		encerraConexaocomBanco();
	}
}
