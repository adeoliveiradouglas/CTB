package main;

import dao.UsuarioDAO;
import entity.Usuario;

public class Teste {

	public static void main(String[] args) {
		for(Usuario u: new UsuarioDAO().getAllByCargo("Presidente"))
			System.out.println(u.getNome());
	}
}