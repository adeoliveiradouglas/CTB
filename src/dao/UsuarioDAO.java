package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import entity.Usuario;

public class UsuarioDAO extends DAO{

	public UsuarioDAO(String nomeDB, String usuarioDB, String senhaDB){
		super(nomeDB, usuarioDB, senhaDB, "usuario");		
	}
	
	@Override
	public void inserir(Usuario usuario){ 
		//Insere usuario no banco
		//caso houver erro, retorna falso
		super.iniciaConexaoComBanco();
		super.setSqlQuery("insert into " +
				   		  super.getNomeTabela() + //nome da tabela
				   		  " (nome, login, senha, ativo)" + //campos para inserir na tabela 
				   		  " values (?,?,?,?)");
				
		try {
			super.setStatement(super.getDbConnection().prepareStatement(super.getSqlQuery()));
			super.getStatement().setString(1, usuario.getNome());
			super.getStatement().setString(2, usuario.getLogin());
			super.getStatement().setString(3, usuario.getSenha());
			super.getStatement().setBoolean(4, true);
			
			super.getStatement().execute();
			super.getStatement().close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.encerraConexaocomBanco();
		}
	}
	
	@Override
	public void atualizar(Usuario usuario){
		super.iniciaConexaoComBanco();
		
		super.setSqlQuery("update " + super.getNomeTabela() + 
				   		  " set" +
				   		  " nome = ?" + 
				   		  " login = ?" + 
				   		  " senha = ?" +  
				   		  " ativo = ?" + 
				   		  " where idUsuario = ?");
		
		try {
			super.setStatement(super.getDbConnection().prepareStatement(super.getSqlQuery()));
			super.getStatement().setString(1, usuario.getNome());
			super.getStatement().setString(2, usuario.getLogin());
			super.getStatement().setString(3, usuario.getSenha());
			super.getStatement().setBoolean(4, usuario.isAtivo());
			super.getStatement().setInt(5, usuario.getId());
			
			super.getStatement().execute();
			super.getStatement().close();
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			super.encerraConexaocomBanco();
		}
	}
	
	@Override
	public void delete(Usuario usuario){
		super.iniciaConexaoComBanco();
		super.encerraConexaocomBanco();
	}
	
	@Override
	public Usuario getByName(String nome){
		//select que retorna um �nico usu�rio pesquisado pelo nome
		super.iniciaConexaoComBanco();
		super.setSqlQuery("select * from " +
						  super.getNomeTabela() + //nome da tabela
				   		  " where nome = ?"); //campos para pesquisar na tabela
		Usuario usuario = null;
		
		try {
			//Buscar usu�rio no banco
			//prepara o Statement
			super.setStatement(super.getDbConnection().prepareStatement(super.getSqlQuery()));
			//completa as ? do Statement
			super.getStatement().setString(1, nome);
			//executa a query e guarda na variavel super.select
			super.setSelect(super.getStatement().executeQuery());
			super.getStatement().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			//Traduz o resultado para um objeto Usuario
			usuario = new Usuario();
			usuario.setId(super.getSelect().getInt("id"));
			usuario.setNome(super.getSelect().getString("nome"));
			usuario.setLogin(super.getSelect().getString("login"));
			usuario.setSenha(super.getSelect().getString("senha"));
			usuario.setAtivo(super.getSelect().getBoolean("ativo"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.encerraConexaocomBanco();
		}
		
		return usuario; 
	}
	
	@Override
	public ArrayList<Usuario> getAll(){
		//select que retorna todos os usu�rios cadastrados no banco
		super.iniciaConexaoComBanco();		

		setSqlQuery("select * from " +
				    getNomeTabela());  
		ArrayList<Usuario> usuarios = new ArrayList<>(); //guarda os usuarios da query
		
		try { //Buscar usu�rios no banco
			super.setStatement(super.getDbConnection().prepareStatement(super.getSqlQuery()));			
			super.setSelect(super.getStatement().executeQuery());
			super.getStatement().close();			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		try {
			//Traduz o resultado para um objeto Usuario e insere na lista
			while(getSelect().next()){
				Usuario usuario = new Usuario();
				usuario.setId(getSelect().getInt("id"));
				usuario.setNome(getSelect().getString("nome"));
				usuario.setLogin(getSelect().getString("login"));
				usuario.setSenha(getSelect().getString("senha"));
				usuario.setAtivo(getSelect().getBoolean("ativo"));
				//insere na lista
				usuarios.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			encerraConexaocomBanco();
		}
		
		return usuarios;
	}
}	
	
