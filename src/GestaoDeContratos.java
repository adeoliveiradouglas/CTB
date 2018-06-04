import java.util.ArrayList;

import dao.*;
import entity.*;

/*
 * Classe main não utilizada no decorrer do programa
 * Usada somente para testes durante o desenvolvimento
 * senha criptografada  com spring security asd = $2a$10$k1SV2r4SH9DcZloBEndktOo2ePCxYeSCOBzpmWuMJg3WHIYRSMZ62 
 */

public class GestaoDeContratos {

	public static void main(String[] args) {
		String usuarioB = "servidorApp",
				banco = "gestaodecontratos",
				senhaB = "suporte2017";
		
//		Usuario u2 = new Usuario(88552233, "Douglas", "adeoliveiradouglas@gmail.com", "$2a$10$k1SV2r4SH9DcZloBEndktOo2ePCxYeSCOBzpmWuMJg3WHIYRSMZ62", "CTB/ TECI", "Administrador");
		SetorDAO sdao = new SetorDAO(banco, usuarioB, senhaB);
		UsuarioDAO udao = new UsuarioDAO(banco, usuarioB, senhaB);
		CargoDAO cdao = new CargoDAO(banco, usuarioB, senhaB);
		
				ArrayList<Setor> setores = sdao.getAll();
		ArrayList<Cargo> cargos = cdao.getAll();
		Usuario u = udao.getByMatricula(88552233);
		ArrayList<Usuario> usuarios = udao.getAll();
		
		System.out.println(u.toString());
		for (int i = 0 ; i < cargos.size(); ++i){
			System.out.println(cargos.get(i).toString());
		}
		for (int i = 0 ; i < setores.size(); ++i){
			System.out.println(setores.get(i).toString());
		}
		for (int i = 0 ; i < usuarios.size(); ++i){
			System.out.println(usuarios.get(i).toString());
		}
		
	}
}
