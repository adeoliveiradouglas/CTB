package main;

import dao.UsuarioDAO;

public class Teste {

	public static void main(String[] args) {
		System.out.println(new UsuarioDAO().getAllByCargo("Diretor"));
		
		/*ArrayList<Cargo> cargos = new ArrayList<>();
		
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
		);*/
	}
}