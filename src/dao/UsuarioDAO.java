/*
 * Classe responsável por acessar os dados de usuario no banco de dados
 */

package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Usuario;
import sun.misc.BASE64Encoder;

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
		
//		criptografa senha do usuario antes de inserir no banco
		usuario.setSenha(
			criptografa(usuario.getSenha())
		);
		
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
		}
		
		encerraConexaocomBanco();
		return lu;
	}
	
	private String criptografa(String senha){
		/*Código retirado do site http://www.guj.com.br/t/cadastro-de-usuario-com-senha-criptografada/192679*/		
		try{
		 MessageDigest digest = MessageDigest.getInstance("SHA-256");
		               digest.update(senha.getBytes());
		 BASE64Encoder encoder = new BASE64Encoder();
		        return encoder.encode(digest.digest());
		}catch(NoSuchAlgorithmException ns){
			ns.printStackTrace();
		}
		return senha;
	}
}
