/*
 * Classe responsável por acesso as tabelas cargo_has_usuario e cargo_has_usuariosnovos 
 */

package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import entity.Cargo;
import entity.Usuario;
import utilidades.ComparadorNome;

public class Cargo_has_usuario extends DAO{
	private final String colunaUsuario = getNomeTabela() + ".idUsuario",
						 colunaCargo = getNomeTabela() + ".cargo_id";
	
	public Cargo_has_usuario(String tabela) {
		super("cargo_has_" + tabela);
	}
	
	public Cargo_has_usuario(String tabela, Connection conexao) {
		super("cargo_has_" + tabela, conexao);
	}
	
	public void inserir(Usuario u){
		iniciaConexaoComBanco();
		
		setSqlQuery("insert into " + getNomeTabela() + " values (?,?)");
		
		try{
			int cargosParaProcessar = 2;
			
			if(u.getCargo().get(0).getId() == u.getCargo().get(1).getId())
				cargosParaProcessar = 1;
			
			setStatement(getDbConnection().prepareStatement(getSqlQuery()));
			
			for (int i = 0; i < cargosParaProcessar; ++i){
				int posicao = 1;
				
				getStatement().setInt(
					posicao, 
					u.getId()
				);
				
				getStatement().setInt(
					++posicao, 
					u.getCargo().get(i).getId()
				);
				
				getStatement().executeUpdate();
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		encerraConexaocomBanco();
	}
	
	public List<Cargo> getByUsuario(int idUsuario){
		/*
		 * retorna os cargos de um usuário 
		 */
		
		List<Cargo> cargos = new ArrayList<Cargo>();
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
			cargos.add(new Cargo());
			cargos.add(new Cargo());
		}
		
		encerraConexaocomBanco();
		return cargos;
	}
	
	public List<Usuario> getByCargo(int idCargo){
		/*
		 * retorna todos os usuários que tem um determinado cargo 
		 */
		List<Usuario> usuarios = new ArrayList<Usuario>();
		iniciaConexaoComBanco();
		
		setSqlQuery("select * from " + getNomeTabela() + " where " + colunaCargo + " = ? and "+colunaUsuario+" > -1");
		
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
			usuarios = new ArrayList<Usuario>();
		}
		
		if(usuarios.size() > 1)
			Collections.sort(usuarios, new ComparadorNome());
		
		encerraConexaocomBanco();
		return usuarios;
	}

	public void atualizar(Usuario usuario) {
		deletar(usuario.getId());
		inserir(usuario);		
	}
	
	public void deletar(int id){
		iniciaConexaoComBanco();
		
		/*Exemplo
		 * delete from usuario where id = ?; 
		 */
		setSqlQuery(
			"delete from " + getNomeTabela() + " where " + colunaUsuario + " = ?"
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

}
