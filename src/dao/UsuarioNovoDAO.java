/*
 * Classe responsavel por acessar os novos usuarios do sistemas
 * Novos usuarios deverão passar pela avaliação do usuario Administrador para poder acessar o sistema 
 * Somente usuarios na tabela Usuario tem acesso ao sistema 
 */

package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Usuario;

public class UsuarioNovoDAO extends DAO {
	//Nome das colunas no banco de dados
	private final String colunaMatricula = super.getNomeTabela() + ".matricula", 
						 colunaNome = super.getNomeTabela() + ".nome", 
						 colunaEmail = super.getNomeTabela() + ".login",
						 colunaSenha = super.getNomeTabela() + ".senha",
						 colunaSetor = super.getNomeTabela() + ".setor_codigo",
						 colunaCargo = super.getNomeTabela() + ".cargo_id";


	public UsuarioNovoDAO(String nomeDB, String usuarioDB, String senhaDB, String ip) {
		super(nomeDB, usuarioDB, senhaDB, "usuariosnovos", ip);
	}

	public UsuarioNovoDAO(String nomeDB, String usuarioDB, String senhaDB) {
		super(nomeDB, usuarioDB, senhaDB, "usuariosnovos");
	}
	
	public UsuarioNovoDAO(){
		super("usuariosnovos");
	}

	public void deleteByMatricula(int matricula){
		iniciaConexaoComBanco();
		
		/*Exemplo
		 * delete from usuariosnovos where matricula = ?; 
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		encerraConexaocomBanco();
	}
		
	public Usuario getByEmail(String email) {
		iniciaConexaoComBanco();
		Usuario u;
		
/*		monta a query
			
 		Exemplo de query para esse método
 		
 		select * from usuariosnovos where usuariosnovos.login = ?";	
*/
		super.setSqlQuery(
				"select * from " + super.getNomeTabela() + " where " + colunaEmail + "= ?"
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
					
			);
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
			
//			busca setor de acordo com o resultado do usuario e salva somente sigla como na obs1 da classe Usuario
			u.setSetor(
				new SetorDAO(super.getNomeBanco(), super.getUsuarioBanco(), super.getSenhaBanco(), super.getIp())
				.getByCodigo(
					super.getResultado().getString(colunaSetor) //codigo do setor no usuario do banco
				).getSigla()
			);
			
			u.setCargo(
				new CargoDAO(super.getNomeBanco(), super.getUsuarioBanco(), super.getSenhaBanco(), super.getIp())
				.getByCodigo(
					super.getResultado().getInt(colunaCargo) //codigo do cargo no usuario do banco
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
 		
 		select * from usuariosnovos";
 		depois busca setor e cargo através do resultado do usuario
 		
*/
		int idCargo;
		String codigoSetor;
		SetorDAO sdao;
		CargoDAO cdao;
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
				u.setSetor(
					sdao.getByCodigo(codigoSetor).getSigla()
				);
				u.setCargo(
					cdao.getByCodigo(idCargo).getNome()
				);
				
//				Adiciona usuario na lista
				lu.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		encerraConexaocomBanco();
		return lu;
	}
	
	
}
