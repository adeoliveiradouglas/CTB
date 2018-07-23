/*
 * Classe responsável por acessar os dados de usuario no banco de dados
 */

package dao;

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
						 colunaCargo = getNomeTabela() + ".cargo_id",
						 ordenarPorNome = " order by " + colunaNome;

	/*
	 * public UsuarioDAO(String nomeDB, String usuarioDB, String senhaDB){
	 * super(nomeDB, usuarioDB, senhaDB, "usuario"); }
	 */

	public UsuarioDAO(){
		super("usuario");
	}
	
	public UsuarioDAO(String nomeTabela){
//		para uso da tabela usuariosnovos
		super(nomeTabela);
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
			colunaCargo + 
			") values (?,?,?,?,?,?)"
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
				usuario.getCargo().getId()
			);
			
			getStatement().executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);;
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
			
		} catch(SQLException e) {
			System.out.println(e);;
			encerraConexaocomBanco();
			return null;
		}
		
		
		try{
			getResultado().next();
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
				
//				busca setor de acordo com o resultado do usuario e salva somente sigla como na obs1 da classe Usuario
				new SetorDAO().getByCodigo(
					getResultado().getString(colunaSetor)
				),
				new CargoDAO().getById(
					getResultado().getInt(colunaCargo)
				)
			);
		} catch (SQLException e) {
			u = null;
			System.out.println(e);;
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
			
		} catch(SQLException e) {
			System.out.println(e);;
			encerraConexaocomBanco();
			return null;
		}
		
		
		try{
			getResultado().next();
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
				
//				busca setor de acordo com o resultado do usuario e salva somente sigla como na obs1 da classe Usuario
				new SetorDAO().getByCodigo(
						getResultado().getString(colunaSetor)
				),
				new CargoDAO().getById(
						getResultado().getInt(colunaCargo)
				)
			);
			
		} catch (SQLException e) {
			u = null;
			System.out.println(e);;
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
			
		} catch(SQLException e) {
			System.out.println(e);;
			encerraConexaocomBanco();
			return null;
		}
		
		
		try{
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
					new SetorDAO().getByCodigo(
							getResultado().getString(colunaSetor)
					),
					new CargoDAO().getById(
							getResultado().getInt(colunaCargo)
					)
				);
				lu.add(u);
			}
		} catch (SQLException e) {
			System.out.println(e);;
			lu = null;
		}
		
		encerraConexaocomBanco();
		return lu;
	}
	
	public ArrayList<Usuario> getAllGestor() {
		iniciaConexaoComBanco();
		
/*		
 		Exemplo de query para esse método
 		
 		select * from usuario where cargo = 3";
 		depois busca setor e cargo através do resultado do usuario
 		
*/
		
		ArrayList<Usuario> lu = new ArrayList<>();
		
//		monta a query
		setSqlQuery(
			"select * from " + getNomeTabela() + " where " + colunaCargo + " = ?" + ordenarPorNome
		);
		
		try {
//			monta o statement
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
			getStatement().setInt(
				1, 
				3 //id do cargo gestor
			);
						
//			executa
			setResultado(
				getStatement().executeQuery()
			);
			
		} catch(SQLException e) {
			System.out.println(e);;
			encerraConexaocomBanco();
			return null;
		}
		
		
		try{
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
					
//					busca setor de acordo com o resultado do usuario e salva somente sigla como na obs1 da classe Usuario
					new SetorDAO().getByCodigo(
							getResultado().getString(colunaSetor)
					),
					new CargoDAO().getById(
							getResultado().getInt(colunaCargo)
					)
				);
				lu.add(u);
			}
		} catch (SQLException e) {
			System.out.println(e);;
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
			System.out.println(e);;
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
			System.out.println(e);;
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
			colunaCargo + " = ? " +
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
				usuario.getCargo().getId()
			);
			
			getStatement().setInt(
				++posicao,
				usuario.getId()
			);
				
			getStatement().executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);;
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
					
				} catch(SQLException e) {
					System.out.println(e);;
					encerraConexaocomBanco();
					return null;
				}
				
				
				try{
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
							new SetorDAO().getByCodigo(
									getResultado().getString(colunaSetor)
							),
							new CargoDAO().getById(
									getResultado().getInt(colunaCargo)
							)
						);
						lu.add(u);
					}
				} catch (SQLException e) {
					System.out.println(e);;
					lu = null;
				}
				
				encerraConexaocomBanco();
				return lu;
	}
}
