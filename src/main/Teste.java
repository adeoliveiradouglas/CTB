package main;

import utilidades.AvisoVencimento;

public class Teste {

	public static void main(String[] args) {
		AvisoVencimento av= new AvisoVencimento();
		
		av.run();
		
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