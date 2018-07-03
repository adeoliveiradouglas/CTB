/*
 * Classe responsável por acessar os dados de usuario no banco de dados
 */

package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Usuario;

public class UsuarioDAO extends DAO {
	//Nome das colunas no banco de dados
	private final String colunaMatricula = super.getNomeTabela() + ".matricula", 
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
	
	public void inserir(Usuario usuario){
		iniciaConexaoComBanco();
		
		super.setSqlQuery(
			"insert into " + super.getNomeTabela() + " values (?,?,?,?,?,?)"
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
			
			super.getStatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		encerraConexaocomBanco();
	}
	
	public Usuario getByMatricula(int matricula) {
		iniciaConexaoComBanco();
		
		
 	/*	Exemplo de query para esse método
 		
 		select * from usuario where usuario.login = ?";
 		depois busca setor e cargo através do resultado do usuario*/
 		

		Usuario u;
		
//		monta a query
		super.setSqlQuery(
			"select * from usuario where " + colunaMatricula +" = ?"
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
				matricula
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
				"select * from usuario where " + colunaEmail + " = ?"
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
		Usuario u = null;
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
			while(super.getResultado().next()){
				u = new Usuario(
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
			"update " + super.getNomeTabela() + " set senha = ? where " + colunaEmail + " = ?"
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

	
	public void deleteByMatricula(int matricula) {
		iniciaConexaoComBanco();
		
		/*Exemplo
		 * delete from usuario where matricula = ?; 
		 */
		super.setSqlQuery(
			"delete from " + super.getNomeTabela() + " where matricula = ?"
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
			
			super.getStatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		encerraConexaocomBanco();	
	}
}
