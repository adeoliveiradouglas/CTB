/*
 * Classe responsável por acessar os dados de usuario no banco de dados
 */

package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Usuario;

public class UsuarioDAO extends DAO {
	//Nome das colunas no banco de dados
	private final String colunaId = super.getNomeTabela() + ".idUsuario",
						 colunaMatricula = super.getNomeTabela() + ".matricula", 
						 colunaNome = super.getNomeTabela() + ".nome", 
						 colunaEmail = super.getNomeTabela() + ".login",
						 colunaSenha = super.getNomeTabela() + ".senha",
						 colunaSetor = super.getNomeTabela() + ".setor_codigo",
						 colunaCargo = super.getNomeTabela() + ".cargo_id";

	/*
	 * public UsuarioDAO(String nomeDB, String usuarioDB, String senhaDB){
	 * super(nomeDB, usuarioDB, senhaDB, "usuario"); }
	 */

	public UsuarioDAO(String nomeDB, String usuarioDB, String senhaDB, String ip) {
		super(nomeDB, usuarioDB, senhaDB, "usuario", ip);
	}

	public UsuarioDAO(String nomeDB, String usuarioDB, String senhaDB) {
		super(nomeDB, usuarioDB, senhaDB, "usuario");
	}

	public UsuarioDAO(){
		super("usuario");
	}
	
	public UsuarioDAO(String nomeTabela){
//		para uso da tabela usuariosnovos
		super(nomeTabela);
	}
	
	public void inserir(Usuario usuario){
		iniciaConexaoComBanco();
		
		super.setSqlQuery(
			"insert into " + super.getNomeTabela() + " (matricula, nome, login, senha, setor_codigo, cargo_id) values (?,?,?,?,?,?)"
		);
		
		int posicao = 0;
		
		try {
			super.setStatement(
				super.getDbConnection().prepareStatement(
					super.getSqlQuery()
				)
			);
			
			super.getStatement().setInt(
				++posicao,
				usuario.getMatricula()
			);
			
			super.getStatement().setString(
				++posicao,
				usuario.getNome()
			);
			
			super.getStatement().setString(
				++posicao,
				usuario.getEmail()
			);
			
			super.getStatement().setString(
				++posicao,
				usuario.getSenha()
			);
			
			super.getStatement().setString(
				++posicao,
				new SetorDAO(super.getNomeBanco(), super.getUsuarioBanco(), super.getSenhaBanco(), super.getIp())
				.getBySigla( //busca o codigo da sigla na tabela de setores
					usuario.getSetor()
				).getCodigo()
			);
			
			super.getStatement().setInt(
				++posicao,
				new CargoDAO(super.getNomeBanco(), super.getUsuarioBanco(), super.getSenhaBanco(), super.getIp())
				.getByNome( //busca o codigo do cargo na tabela de cargos
					usuario.getCargo()
				).getId()
			);
			
			super.getStatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		encerraConexaocomBanco();
	}
	
	public Usuario getById(int id) {
		iniciaConexaoComBanco();
		
		
 	/*	Exemplo de query para esse método
 		
 		select * from usuario where usuario.id = ?";
 		depois busca setor e cargo através do resultado do usuario*/
 		

		Usuario u;
		
//		monta a query
		super.setSqlQuery(
			"select * from " + super.getNomeTabela() + " where " + colunaId +" = ?"
		);
		
		try {
//			monta o statement
			super.setStatement(
				super.getDbConnection().prepareStatement(
					super.getSqlQuery()
				)
			);
			
//			Preenche o statement
			super.getStatement().setInt(
				1, 
				id
			);
			
//			executa
			super.setResultado(
				super.getStatement().executeQuery()
			);
			
		} catch(SQLException e) {
			e.printStackTrace();
			encerraConexaocomBanco();
			return null;
		}
		
		
		try{
			super.getResultado().next();
			u = new Usuario(
				super.getResultado().getInt(
					colunaId
				),
				super.getResultado().getInt(
					colunaMatricula
				),
				super.getResultado().getString(
					colunaNome
				),
				super.getResultado().getString(
					colunaEmail
				),
				super.getResultado().getString(
					colunaSenha
				),
				
//				busca setor de acordo com o resultado do usuario e salva somente sigla como na obs1 da classe Usuario
				new SetorDAO().getByCodigo(
						super.getResultado().getString(colunaSetor)
				).getSigla(),
				new CargoDAO().getByCodigo(
						super.getResultado().getInt(colunaCargo)
				).getNome()
			);
		} catch (SQLException e) {
			u = null;
			e.printStackTrace();
		}
		
		encerraConexaocomBanco();
		return u;
	}
	
	public Usuario getByEmail(String email) {
		iniciaConexaoComBanco();
		
/*		
 		Exemplo de query para esse método
 		
 		select * from usuario where usuario.login = ?";
 		depois busca setor e cargo através do resultado do usuario
 		
*/
		Usuario u;
		
//		monta a query
		super.setSqlQuery(
			"select * from " + super.getNomeTabela() + " where " + colunaEmail + " = ?"
		);
		
		try {
//			monta o statement
			super.setStatement(
				super.getDbConnection().prepareStatement(
					super.getSqlQuery()
				)
			);
			
//			Preenche o statement
			super.getStatement().setString(
				1, 
				email
			);
			
//			executa
			super.setResultado(
				super.getStatement().executeQuery()
			);
			
		} catch(SQLException e) {
			e.printStackTrace();
			encerraConexaocomBanco();
			return null;
		}
		
		
		try{
			super.getResultado().next();
			u = new Usuario(
				super.getResultado().getInt(
					colunaId
				),
				super.getResultado().getInt(
					colunaMatricula
				),
				super.getResultado().getString(
					colunaNome
				),
				super.getResultado().getString(
					colunaEmail
				),
				super.getResultado().getString(
					colunaSenha
				),
				
//				busca setor de acordo com o resultado do usuario e salva somente sigla como na obs1 da classe Usuario
				new SetorDAO().getByCodigo(
						super.getResultado().getString(colunaSetor)
				).getSigla(),
				new CargoDAO().getByCodigo(
						super.getResultado().getInt(colunaCargo)
				).getNome()
			);
			
		} catch (SQLException e) {
			u = null;
			e.printStackTrace();
		}
		
		encerraConexaocomBanco();
		return u;
	}

	public ArrayList<Usuario> getAll() {
		iniciaConexaoComBanco();
		
/*		
 		Exemplo de query para esse método
 		
 		select * from usuario";
 		depois busca setor e cargo através do resultado do usuario
 		
*/
		
		ArrayList<Usuario> lu = new ArrayList<>();
		
//		monta a query
		super.setSqlQuery(
			"select * from " + super.getNomeTabela()
		);
		
		try {
//			monta o statement
			super.setStatement(
				super.getDbConnection().prepareStatement(
					super.getSqlQuery()
				)
			);
						
//			executa
			super.setResultado(
				super.getStatement().executeQuery()
			);
			
		} catch(SQLException e) {
			e.printStackTrace();
			encerraConexaocomBanco();
			return null;
		}
		
		
		try{
			Usuario u = null;
			
			while(super.getResultado().next()){
				u = new Usuario(
					super.getResultado().getInt(
						colunaId
					),
					super.getResultado().getInt(
						colunaMatricula
					),
					super.getResultado().getString(
						colunaNome
					),
					super.getResultado().getString(
						colunaEmail
					),
					super.getResultado().getString(
						colunaSenha
					),
					
//					busca setor de acordo com o resultado do usuario e salva somente sigla como na obs1 da classe Usuario
					new SetorDAO().getByCodigo(
							super.getResultado().getString(colunaSetor)
					).getSigla(),
					new CargoDAO().getByCodigo(
							super.getResultado().getInt(colunaCargo)
					).getNome()
				);
				lu.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			lu = null;
		}
		
		encerraConexaocomBanco();
		return lu;
	}

	public void atualizarSenha(String senha, String email){
		iniciaConexaoComBanco();
		
		/*
		  Exemplo de query
		  update usuario set senha = senha where usuario.login = email;
		*/
		super.setSqlQuery(
			"update " + super.getNomeTabela() + " set " + colunaSenha + " = ? where " + colunaEmail + " = ?"
		);
		
		try {
			int posicao = 0;
			super.setStatement(
				super.getDbConnection().prepareStatement(
					super.getSqlQuery()
				)
			);
			
			super.getStatement().setString(
				++posicao, 
				senha
			);
			
			super.getStatement().setString(
				++posicao, 
				email
			);
			
			super.getStatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		encerraConexaocomBanco();
	}
	
	public void deleteById(int id) {
		iniciaConexaoComBanco();
		
		/*Exemplo
		 * delete from usuario where matricula = ?; 
		 */
		super.setSqlQuery(
			"delete from " + super.getNomeTabela() + " where " + colunaId + " = ?"
		);
		
		try {
			super.setStatement(
				super.getDbConnection().prepareStatement(
					super.getSqlQuery()
				)
			);
			
			super.getStatement().setInt(
				1,
				id
			);
			
			super.getStatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		encerraConexaocomBanco();	
	}

	public void atualizar(Usuario usuario){
		iniciaConexaoComBanco();
		
		/*
		  Exemplo de query
		  update usuario set todos os parametros where usuario.login = email;
		*/
		super.setSqlQuery(
			"update " + super.getNomeTabela() + " set " + 
			colunaMatricula + " = ?, " + 
			colunaNome + " = ?, " +
			colunaEmail + " = ?, " +
			colunaSenha + " = ?, " +
			colunaSetor + " = ?, " +
			colunaCargo + " = ? " +
			"where " + colunaId + " = ?"
		);
		
		
		int posicao = 0;
		SetorDAO sdao = new SetorDAO(super.getNomeBanco(), super.getUsuarioBanco(), super.getSenhaBanco(), super.getIp());
		CargoDAO cdao = new CargoDAO(super.getNomeBanco(), super.getUsuarioBanco(), super.getSenhaBanco(), super.getIp());
		
		try {
			super.setStatement(
				super.getDbConnection().prepareStatement(
					super.getSqlQuery()
				)
			);
			
			super.getStatement().setInt(
				++posicao,
				usuario.getMatricula()
			);
			
			super.getStatement().setString(
				++posicao,
				usuario.getNome()
			);
			
			super.getStatement().setString(
				++posicao,
				usuario.getEmail()
			);
			
			super.getStatement().setString(
				++posicao,
				usuario.getSenha()
			);
			
			super.getStatement().setString(
				++posicao,
				sdao.getBySigla( //busca o codigo da sigla na tabela de setores
					usuario.getSetor()
				).getCodigo()
			);
			
			super.getStatement().setInt(
				++posicao,
				cdao.getByNome( //busca o codigo do cargo na tabela de cargos
					usuario.getCargo()
				).getId()
			);
			
			super.getStatement().setInt(
				++posicao,
				usuario.getId()
			);
				
			super.getStatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		encerraConexaocomBanco();
	}
}
