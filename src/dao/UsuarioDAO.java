/*
 * Classe responsável por acessar os dados de usuario no banco de dados
 */

package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Usuario;

public class UsuarioDAO extends DAO {
	//Nome das colunas no banco de dados
	@SuppressWarnings("unused")
	private final String colunaMatricula = super.getNomeTabela() + ".matricula", 
						 colunaNome = super.getNomeTabela() + ".nome", 
						 colunaEmail = super.getNomeTabela() + ".email",
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

	public void inserir(Usuario usuario) {
		iniciaConexaoComBanco();
		super.setSqlQuery(
			"insert into " + super.getNomeTabela() + " values (?,?,?,?,?)"
		);
		encerraConexaocomBanco();
	}

	public Usuario getByEmail(String email) {
		iniciaConexaoComBanco();
		
/*		
 		Exemplo de query para esse método
 		
 		select usuario.matricula, 
			   usuario.nome, 
			   usuario.login, 
			   usuario.senha, 
			   setor.sigla, 
			   cargo.nome 
		from (
			(usuario inner join setor on usuario.setor_codigo = setor.codigo) 
			inner join cargo on usuario.cargo_id = cargo.id
		) 
		where usuario.login = ?";
*/
		
//		monta a query
		SetorDAO s = new SetorDAO();
		CargoDAO c = new CargoDAO();
		super.setSqlQuery(
			"select " +
				colunaMatricula + ", " +
				colunaNome  + ", " +
				colunaEmail  + ", " +
				colunaSenha  + ", " +
				s.getColunaSigla()  + ", " + 
				c.getColunaNome() +
			"from ((usuario inner join setor on usuario.setor_codigo = setor.codigo) inner join cargo on usuario.cargo_id = cargo.id) where usuario.login = ?"
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
		
		Usuario u = null;
		try{
			super.getResultado().next();
			u = new Usuario(
				super.getResultado().getInt(colunaMatricula),
				super.getResultado().getString(colunaNome),
				super.getResultado().getString(colunaEmail),
				super.getResultado().getString(colunaSenha),
				super.getResultado().getString(s.getColunaSigla()),
				super.getResultado().getString(c.getColunaNome())
			);
		} catch (SQLException e) {
			u = null;
			e.printStackTrace();
		}
		
		encerraConexaocomBanco();
		return u;
	}

	public ArrayList<Usuario> getAll() {
		return null;
	}

	/*private ArrayList<Usuario> traduzirResultset() {
		return null;
	}*/
}
