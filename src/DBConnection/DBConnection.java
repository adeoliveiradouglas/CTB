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

	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://" + ip + ":" + porta +"/" + nomeBanco, usuarioBanco, senhaBanco);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public DBConnection() {
		
	}

	public DBConnection(String nomeBanco, String usuarioBanco, String senhaBanco) {
		super();
		this.nomeBanco = nomeBanco;
		this.usuarioBanco = usuarioBanco;
		this.senhaBanco = senhaBanco;
	}

	public DBConnection(String ip, String porta, String nomeBanco, String usuarioBanco, String senhaBanco) {
		super();
		this.ip = ip;
		this.porta = porta;
		this.nomeBanco = nomeBanco;
		this.usuarioBanco = usuarioBanco;
		this.senhaBanco = senhaBanco;
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPorta() {
		return porta;
	}

	public void setPorta(String porta) {
		this.porta = porta;
	}

	public String getNomeBanco() {
		return nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	public String getUsuarioBanco() {
		return usuarioBanco;
	}

	public void setUsuarioBanco(String usuarioBanco) {
		this.usuarioBanco = usuarioBanco;
	}

	public String getSenhaBanco() {
		return senhaBanco;
	}

	public void setSenhaBanco(String senhaBanco) {
		this.senhaBanco = senhaBanco;
	}
}
