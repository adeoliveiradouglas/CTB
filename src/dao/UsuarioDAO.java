/*
 * Classe responsável por acessar os dados de usuario no banco de dados
 */

package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Usuario;

public class UsuarioDAO extends DAO {
	//Nome das colunas no banco de dados
	private final String colunaId = getNomeTabela() + ".idUsuario",
						 colunaMatricula = getNomeTabela() + ".matricula", 
						 colunaNome = getNomeTabela() + ".nome", 
						 colunaEmail = getNomeTabela() + ".login",
						 colunaSenha = getNomeTabela() + ".senha",
						 colunaSetor = getNomeTabela() + ".setor_codigo",
						 ordenarPorNome = " order by " + colunaNome;

	/*
	 * public UsuarioDAO(String nomeDB, String usuarioDB, String senhaDB){
	 * super(nomeDB, usuarioDB, senhaDB, "usuario"); }
	 */

	public UsuarioDAO(){
		super("usuario");
	}
	
	public UsuarioDAO(Connection conexao){
		super("usuario", conexao);
	}
	
	public UsuarioDAO(boolean novosUsuarios){
/*		para uso da tabela usuariosnovos
 *		acessa tabela de novos usuários não importando qual o valor do parãmetro 
 */		
		super("usuariosnovos");
	}
	
	public void inserir(Usuario usuario){
		iniciaConexaoComBanco();
		
		setSqlQuery(
			"insert into " + getNomeTabela() + " (" +
			colunaMatricula + ", " + 
			colunaNome + ", " + 
			colunaEmail + ", " + 
			colunaSenha + ", " + 
			colunaSetor + ", " + 
			") values (?,?,?,?,?)"
		);
		
		int posicao = 0;
		
		try {
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
			getStatement().setInt(
				++posicao,
				usuario.getMatricula()
			);
			
			getStatement().setString(
				++posicao,
				usuario.getNome()
			);
			
			getStatement().setString(
				++posicao,
				usuario.getEmail()
			);
			
			getStatement().setString(
				++posicao,
				usuario.getSenha()
			);
			
			getStatement().setString(
				++posicao,
				usuario.getSetor().getCodigo()
			);
						
			getStatement().executeUpdate();
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
 		

		Usuario u = null;
		
//		monta a query
		setSqlQuery(
			"select * from " + getNomeTabela() + " where " + colunaId +" = ?"
		);
		
		try {
//			monta o statement
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
//			Preenche o statement
			getStatement().setInt(
				1, 
				id
			);
			
//			executa
			setResultado(
				getStatement().executeQuery()
			);
			
			if(getResultado().next()){
				u = new Usuario(
					getResultado().getInt(
						colunaId
					),
					getResultado().getInt(
						colunaMatricula
					),
					getResultado().getString(
						colunaNome
					),
					getResultado().getString(
						colunaEmail
					),
					getResultado().getString(
						colunaSenha
					),					
					
//					busca setor de acordo com o resultado do usuario e salva somente sigla como na obs1 da classe Usuario
					new SetorDAO(getDbConnection()).getByCodigo(
						getResultado().getString(colunaSetor)
					),
					new Cargo_has_usuario(getNomeTabela(), getDbConnection()).getByUsuario(
						getResultado().getInt(colunaId)
					)
				);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			u = null;
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
		Usuario u = null;
		
//		monta a query
		setSqlQuery(
			"select * from " + getNomeTabela() + " where " + colunaEmail + " = ?"
		);
		
		try {
//			monta o statement
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
//			Preenche o statement
			getStatement().setString(
				1, 
				email
			);
			
//			executa
			setResultado(
				getStatement().executeQuery()
			);
			
			if(getResultado().next()){
				u = new Usuario(
					getResultado().getInt(
						colunaId
					),
					getResultado().getInt(
						colunaMatricula
					),
					getResultado().getString(
						colunaNome
					),
					getResultado().getString(
						colunaEmail
					),
					getResultado().getString(
						colunaSenha
					),
					
//					busca setor de acordo com o resultado do usuario e salva somente sigla como na obs1 da classe Usuario
					new SetorDAO(getDbConnection()).getByCodigo(
							getResultado().getString(colunaSetor)
					),
					new Cargo_has_usuario(getNomeTabela(), getDbConnection()).getByUsuario(
						getResultado().getInt(colunaId)
					)
				);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			u = null;
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
		setSqlQuery(
			"select * from " + getNomeTabela() + ordenarPorNome
		);
		
		try {
//			monta o statement
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
						
//			executa
			setResultado(
				getStatement().executeQuery()
			);
			
			Usuario u = null;
			
			while(getResultado().next()){
				u = new Usuario(
					getResultado().getInt(
						colunaId
					),
					getResultado().getInt(
						colunaMatricula
					),
					getResultado().getString(
						colunaNome
					),
					getResultado().getString(
						colunaEmail
					),
					getResultado().getString(
						colunaSenha
					),
					
//					busca setor de acordo com o resultado do usuario
					new SetorDAO(getDbConnection()).getByCodigo(
							getResultado().getString(colunaSetor)
					),
					new Cargo_has_usuario(getNomeTabela(), getDbConnection()).getByUsuario(
						getResultado().getInt(colunaId)
					)
				);
				lu.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			lu = new ArrayList<>();
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
		setSqlQuery(
			"update " + getNomeTabela() + " set " + colunaSenha + " = ? where " + colunaEmail + " = ?"
		);
		
		try {
			int posicao = 0;
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
			getStatement().setString(
				++posicao, 
				senha
			);
			
			getStatement().setString(
				++posicao, 
				email
			);
			
			getStatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		encerraConexaocomBanco();
	}
	
	public void deleteById(int id) {
		iniciaConexaoComBanco();
		
		/*Exemplo
		 * delete from usuario where id = ?; 
		 */
		setSqlQuery(
			"delete from " + getNomeTabela() + " where " + colunaId + " = ?"
		);
		
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
			
			getStatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		encerraConexaocomBanco();	
	}

	public void atualizar(Usuario usuario){
		iniciaConexaoComBanco();
		
		/*
		  Exemplo de query
		  update usuario set todos os parametros where usuario.id = usuario.getId();
		*/
		setSqlQuery(
			"update " + getNomeTabela() + " set " + 
			colunaMatricula + " = ?, " + 
			colunaNome + " = ?, " +
			colunaEmail + " = ?, " +
			colunaSenha + " = ?, " +
			colunaSetor + " = ?, " +
			"where " + colunaId + " = ?"
		);
		
		int posicao = 0;
		
		try {
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
			getStatement().setInt(
				++posicao,
				usuario.getMatricula()
			);
			
			getStatement().setString(
				++posicao,
				usuario.getNome()
			);
			
			getStatement().setString(
				++posicao,
				usuario.getEmail()
			);
			
			getStatement().setString(
				++posicao,
				usuario.getSenha()
			);
			
			getStatement().setString(
				++posicao,
				usuario.getSetor().getCodigo()
			);
			
			getStatement().setInt(
				++posicao,
				usuario.getId()
			);
				
			getStatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		encerraConexaocomBanco();
	}

	public ArrayList<Usuario> getAllOrdenado(String ordenacao) {
		iniciaConexaoComBanco();
		
/*		
 		Exemplo de query para esse método
 		
 		select * from usuario";
 		depois busca setor e cargo através do resultado do usuario
 		
*/
		
		ArrayList<Usuario> lu = new ArrayList<>();
		
//				monta a query
		setSqlQuery(
			"select * from " + getNomeTabela() + " order by " + ordenacao
		);
		
		try {
//					monta o statement
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
						
//					executa
			setResultado(
				getStatement().executeQuery()
			);
			
			Usuario u = null;
			
			while(getResultado().next()){
				u = new Usuario(
					getResultado().getInt(
						colunaId
					),
					getResultado().getInt(
						colunaMatricula
					),
					getResultado().getString(
						colunaNome
					),
					getResultado().getString(
						colunaEmail
					),
					getResultado().getString(
						colunaSenha
					),
					
//							busca setor de acordo com o resultado do usuario
					new SetorDAO(getDbConnection()).getByCodigo(
						getResultado().getString(colunaSetor)
					),
					new Cargo_has_usuario(getNomeTabela(), getDbConnection()).getByUsuario(
						getResultado().getInt(colunaId)
					)
				);
				lu.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			lu = new ArrayList<>() ;
		}
		
		encerraConexaocomBanco();
		return lu;
	}

	public ArrayList<Usuario> getAllGestor() {
		iniciaConexaoComBanco();
		
		/*
		 * Passo a passo desse método:
		 * 1º: busca na tabela cargo_has_usuario por todos os ids de usuarios que tem o idCargo = 3
		 * 2º: tranforma todos os ids em um objeto Usuario pelo método UsuarioDAO.getById
		 */
		
		ArrayList<Usuario> lu = new Cargo_has_usuario(getNomeTabela(), getDbConnection()).getByCargo(3);
				
		encerraConexaocomBanco();
		return lu;
	}
	
	public ArrayList<Usuario> getAllByCargo(String cargo) {
		int codCargo = new CargoDAO().getByNome(cargo).getId();
		
		iniciaConexaoComBanco();
		
		/*
		 * Passo a passo desse método:
		 * 1º: busca na tabela cargo_has_usuario por todos os ids de usuarios que tem o idCargo = 3
		 * 2º: tranforma todos os ids em um objeto Usuario pelo método UsuarioDAO.getById
		 */
		
		ArrayList<Usuario> lu = new Cargo_has_usuario(getNomeTabela(), getDbConnection()).getByCargo(codCargo);
				
		encerraConexaocomBanco();
		return lu;
	}
}
