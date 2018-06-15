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
	public void inserir(Usuario usuario) {
		iniciaConexaoComBanco();
		super.setSqlQuery(
			"insert into " + super.getNomeTabela() + " values (?,?,?,?,?,?)"
		);
		
		try {
			int posicao = 0;
			SetorDAO sdao = new SetorDAO(super.getNomeBanco(), super.getUsuarioBanco(), super.getSenhaBanco(), super.getIp());
			CargoDAO cdao = new CargoDAO(super.getNomeBanco(), super.getUsuarioBanco(), super.getSenhaBanco(), super.getIp());
			
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		encerraConexaocomBanco();
	}
	
	public Usuario getByMatricula(int matricula) {
		iniciaConexaoComBanco();
		
/*		
 		Exemplo de query para esse método
 		
 		select * from usuario where usuario.login = ?";
 		depois busca setor e cargo através do resultado do usuario
 		
*/
		int idCargo;
		String codigoSetor;
		SetorDAO sdao;
		CargoDAO cdao;
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
			u = new Usuario();
			u.setMatricula(
				super.getResultado().getInt(
					colunaMatricula
				)
			);
			
			u.setNome(
				super.getResultado().getString(
					colunaNome
				)
			);
			
			u.setEmail(
				super.getResultado().getString(
					colunaEmail
				)
			);
			
			u.setSenha(
				super.getResultado().getString(
					colunaSenha
				)
			);
			
			codigoSetor = super.getResultado().getString(colunaSetor);
			idCargo = super.getResultado().getInt(colunaCargo);
			
			sdao = new SetorDAO("gestaodecontratos", "douglas", "administrador", super.getIp());
			cdao = new CargoDAO("gestaodecontratos", "douglas", "administrador", super.getIp());
			
//			busca setor de acordo com o resultado do usuario e salva somente sigla como na obs1 da classe Usuario
			u.setSetor(
				sdao.getByCodigo(
					codigoSetor
				).getSigla()
			);
			
			u.setCargo(
				cdao.getByCodigo(
					idCargo
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
		int idCargo;
		String codigoSetor;
		SetorDAO sdao;
		CargoDAO cdao;
		Usuario u;
		
//		monta a query
		super.setSqlQuery(
				"select * from usuario where usuario.login = ?"
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
			u = new Usuario();
			u.setMatricula(
				super.getResultado().getInt(
					colunaMatricula
				)
			);
			
			u.setNome(
				super.getResultado().getString(
					colunaNome
				)
			);
			
			u.setEmail(
				super.getResultado().getString(
					colunaEmail
				)
			);
			
			u.setSenha(
				super.getResultado().getString(
					colunaSenha
				)
			);
			
			codigoSetor = super.getResultado().getString(colunaSetor);
			idCargo = super.getResultado().getInt(colunaCargo);
			
			sdao = new SetorDAO("gestaodecontratos", "douglas", "administrador", super.getIp());
			cdao = new CargoDAO("gestaodecontratos", "douglas", "administrador", super.getIp());
			
//			busca setor de acordo com o resultado do usuario e salva somente sigla como na obs1 da classe Usuario
			u.setSetor(
				sdao.getByCodigo(
					codigoSetor
				).getSigla()
			);
			
			u.setCargo(
				cdao.getByCodigo(
					idCargo
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
		int idCargo;
		String codigoSetor;
		SetorDAO sdao;
		CargoDAO cdao;
		ArrayList<Usuario> lu = new ArrayList<>();
		
//		monta a query
		super.setSqlQuery(
			"select * from usuario"
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
			return lu;
		}
		
		Usuario u = null;
		sdao = new SetorDAO(super.getNomeBanco(), super.getUsuarioBanco(), super.getSenhaBanco(), super.getIp());
		cdao = new CargoDAO(super.getNomeBanco(), super.getUsuarioBanco(), super.getSenhaBanco(), super.getIp());
		try{
			while(super.getResultado().next()){
				u = new Usuario();
				u.setMatricula(
					super.getResultado().getInt(
						colunaMatricula
					)
				);
				
				u.setNome(
					super.getResultado().getString(
						colunaNome
					)
				);
				
				u.setEmail(
					super.getResultado().getString(
						colunaEmail
					)
				);
				
				u.setSenha(
					super.getResultado().getString(
						colunaSenha
					)
				);
				
				codigoSetor = super.getResultado().getString(colunaSetor);
				idCargo = super.getResultado().getInt(colunaCargo);
				
				//busca setor de acordo com o resultado do usuario e salva somente sigla como na obs1 da classe Usuario
				u.setSetor(sdao.getByCodigo(codigoSetor).getSigla());
				u.setCargo(cdao.getByCodigo(idCargo).getNome());
				lu.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		encerraConexaocomBanco();
		return lu;
	}

}
