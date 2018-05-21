package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private String ip = "localhost",
		    porta = "3306",
		    nomeBanco = "", 
		    usuarioBanco = "root", 
		    senhaBanco = "";
	
	public DBConnection(String ip, String nomeBanco, String usuarioBanco, String senhaBanco) {
		this.ip = ip;
		this.nomeBanco = nomeBanco;
		this.usuarioBanco = usuarioBanco;
		this.senhaBanco = senhaBanco;
	}

	/*public DBConnection(String nomeBanco, String usuarioBanco, String senhaBanco) {
		this.nomeBanco = nomeBanco;
		this.usuarioBanco = usuarioBanco;
		this.senhaBanco = senhaBanco;
	}*/
	
	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://" + ip + ":" + porta +"/" + nomeBanco, usuarioBanco, senhaBanco);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	

	/*public DBConnection(String ip, String porta, String nomeBanco, String usuarioBanco, String senhaBanco) {
		super();
		this.ip = ip;
		this.porta = porta;
		this.nomeBanco = nomeBanco;
		this.usuarioBanco = usuarioBanco;
		this.senhaBanco = senhaBanco;
	}*/
	
	
}
