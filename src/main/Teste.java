package main;

import java.util.ArrayList;

import dao.SetorDAO;
import dao.UsuarioDAO;
import entity.Cargo;
import entity.Usuario;

public class Teste {

	public static void main(String[] args) {
		ArrayList<Cargo> cargos = new ArrayList<>();
		
		cargos.add(new Cargo(1, "Administrador", ""));
		cargos.add(new Cargo(1, "Administrador", ""));
		
		new UsuarioDAO(true).inserir(
			new Usuario(
				0,
				123,
				"123",
				"123@123",
				"teste",
				new SetorDAO().getBySigla("CTB/ TECI"),
				cargos
			)
		);
	}
}