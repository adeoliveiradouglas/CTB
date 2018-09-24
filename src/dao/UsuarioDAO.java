/*
 * Classe responsável por acessar os dados de usuario no banco de dados
 */

package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Cargo;
import entity.Usuario;

public class UsuarioDAO extends DAO {
	//Nome das colunas no banco de dados
	private final String colunaId = getNomeTabela() + ".idUsuario",
						 colunaMatricula = getNomeTabela() + ".matricula", 
						 colunaNome = getNomeTabela() + ".nome", 
						 colunaEmail = getNomeTabela() + ".login",
						 colunaSenha = getNomeTabela() + ".senha",
						 colunaSetor = getNomeTabela() + ".setor_codigo";

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
 *		acessa tabela de novos usuários não importando qual o valor do parâmetro 
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
			colunaSetor +
			") values (?,?,?,?,?)"
		);
		
		int posicao = 1;
		
		try {
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
			getStatement().setInt(
				posicao,
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
			
			usuario.setId(getByEmail(usuario.getEmail()).getId());
			
			//inserir a referência dos cargos na tabela de cargo_has_usuariosnovos
			new Cargo_has_usuario(getNomeTabela(), getDbConnection()).inserir(usuario);
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
		List<Cargo> cargos = null;
		
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
			
			if (getResultado().next()) {
				cargos = new Cargo_has_usuario(getNomeTabela(), getDbConnection())
						.getByUsuario(getResultado().getInt(colunaId));
				
//				Se o usuário só tiver 1 cargo, a posição do segundo fica somente com o id para não gerar erros mais tarde
				if(cargos.size() == 1){
					cargos.add(new Cargo(cargos.get(0).getId() ,"",""));
				}
				
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
					cargos
				);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			u = new Usuario();
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
				List<Cargo> cargos = new Cargo_has_usuario(getNomeTabela(), getDbConnection()).getByUsuario(
					getResultado().getInt(colunaId)
				);
				
//				Se o usuário só tiver 1 cargo, a posição do segundo fica somente com o id para não gerar erros mais tarde 
				if(cargos.size() == 1){
					cargos.add(new Cargo(cargos.get(0).getId() ,"",""));
				}
				
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
//						busca setor de acordo com o resultado do usuario e salva somente sigla como na obs1 da classe Usuario
					new SetorDAO(getDbConnection()).getByCodigo(
						getResultado().getString(colunaSetor)
					),
					cargos
				);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			u = new Usuario();
		}
		
		encerraConexaocomBanco();
		return u;
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
			colunaSetor + " = ? " +
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
			
			new Cargo_has_usuario(getNomeTabela(), getDbConnection()).atualizar(usuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		encerraConexaocomBanco();
	}

	public List<Usuario> getAll() {
		return getAll(colunaNome);
	}
	
	public List<Usuario> getAll(String ordenacao) {
		iniciaConexaoComBanco();
		
/*		
 		Exemplo de query para esse método
 		
 		select * from usuario";
 		depois busca setor e cargo através do resultado do usuario
 		
*/
		
		List<Usuario> lu = new ArrayList<Usuario>();
		
//		monta a query
		setSqlQuery(
			"select * from " + getNomeTabela() + " order by " + ordenacao
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
				List<Cargo> cargos = new Cargo_has_usuario(getNomeTabela(), getDbConnection()).getByUsuario(
					getResultado().getInt(colunaId)
				);
				
//				Se o usuário só tiver 1 cargo, a posição do segundo fica somente com o id para não gerar erros mais tarde
				if(cargos.size() == 1){
					cargos.add(new Cargo(cargos.get(0).getId() ,"",""));
//				Se, por algum erro do banco, o usuário não possuir cargos, é setado a ele dois cargos vazios seguindo a mesma ideia acima
				} else if (cargos.size() == 0){
					cargos.add(new Cargo());
					cargos.add(new Cargo());
				}
				
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
//						busca setor de acordo com o resultado do usuario e salva somente sigla como na obs1 da classe Usuario
					new SetorDAO(getDbConnection()).getByCodigo(
						getResultado().getString(colunaSetor)
					),
					cargos
				);
				lu.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			lu = new ArrayList<Usuario>() ;
		}
		
		encerraConexaocomBanco();
		return lu;
	}

	public List<Usuario> getAllGestor() {
		iniciaConexaoComBanco();
		
		/*
		 * Passo a passo desse método:
		 * 1º: busca na tabela cargo_has_usuario por todos os ids de usuarios que tem o idCargo = 3
		 * 2º: tranforma todos os ids em um objeto Usuario pelo método UsuarioDAO.getById
		 */
		
		List<Usuario> lu = new Cargo_has_usuario(getNomeTabela(), getDbConnection()).getByCargo(3);
				
		encerraConexaocomBanco();
		return lu;
	}
	
	public List<Usuario> getAllByCargo(String cargo) {
		int codCargo = new CargoDAO().getByNome(cargo).getId();
		
		iniciaConexaoComBanco();
		
		/*
		 * Passo a passo desse método:
		 * 1º: busca na tabela cargo_has_usuario por todos os ids de usuarios que tem o idCargo = codCargo
		 * 2º: tranforma todos os ids em um objeto Usuario pelo método UsuarioDAO.getById
		 */
		
		List<Usuario> lu = new Cargo_has_usuario(getNomeTabela(), getDbConnection()).getByCargo(codCargo);
				
		encerraConexaocomBanco();
		return lu;
	}
}
