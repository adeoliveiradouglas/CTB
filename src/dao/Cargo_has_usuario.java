/*
 * Classe responsável por acesso as tabelas cargo_has_usuario e cargo_has_usuariosnovos 
 */

package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Cargo;
import entity.Usuario;

public class Cargo_has_usuario extends DAO{
	private final String colunaUsuario = getNomeTabela() + ".idUsuario",
						 colunaCargo = getNomeTabela() + ".cargo_id";
	
	protected Cargo_has_usuario(String segundaTabela) {
		super("cargo_has_" + segundaTabela);
	}
	
	protected Cargo_has_usuario(String segundaTabela, Connection conexao) {
		super("cargo_has_" + segundaTabela, conexao);
	}

	public void inserir(Usuario u, Cargo c){
		iniciaConexaoComBanco();
		
		setSqlQuery("insert into " + getNomeTabela() + " values (?,?)");
		
		try{
			setStatement(getDbConnection().prepareStatement(getSqlQuery()));
			
			int posicao = 1;
			
			getStatement().setInt(posicao, u.getId());
			getStatement().setInt(++posicao, c.getId());
			
			getStatement().executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		encerraConexaocomBanco();
	}
	
	public ArrayList<Cargo> getByUsuario(int idUsuario){
		/*
		 * retorna os cargos de um usuário 
		 */
		
		ArrayList<Cargo> cargos = new ArrayList<Cargo>();
		iniciaConexaoComBanco();
		
		setSqlQuery("select * from " + getNomeTabela() + " where " + colunaUsuario + " = ?");
		
		try{
//			monta o statement
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
//			Preenche o statement
			getStatement().setInt(
				1, 
				idUsuario
			);
			
//			executa
			setResultado(
				getStatement().executeQuery()
			);

			CargoDAO cdao = new CargoDAO(getDbConnection());
			
			while(getResultado().next()){
				cargos.add(
					cdao.getById(getResultado().getInt(colunaCargo))
				);
			}
		} catch (SQLException e){
			e.printStackTrace();
			cargos = new ArrayList<Cargo>();
		}
		
		encerraConexaocomBanco();
		return cargos;
	}
	
	public ArrayList<Usuario> getByCargo(int idCargo){
		/*
		 * retorna todos os usuários que tem um determinado cargo 
		 */
		ArrayList<Usuario> usuarios = new ArrayList<>();
		iniciaConexaoComBanco();
		
		setSqlQuery("select * from " + getNomeTabela() + " where " + colunaUsuario + " = ?");
		
		try{
//			monta o statement
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
//			Preenche o statement
			getStatement().setInt(
				1, 
				idCargo
			);
			
//			executa
			setResultado(
				getStatement().executeQuery()
			);

			UsuarioDAO udao = new UsuarioDAO(getDbConnection());
			
			while(getResultado().next()){
				usuarios.add(
					udao.getById(getResultado().getInt(colunaUsuario))
				);
			}
		} catch (SQLException e){
			e.printStackTrace();
			usuarios = new ArrayList<>();
		}
		
		encerraConexaocomBanco();
		return usuarios;
	}
	

}
