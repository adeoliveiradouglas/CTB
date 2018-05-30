import java.util.ArrayList;

import dao.SetorDAO;
import dao.UsuarioDAO;
import entity.Setor;

/*
 * Classe main não utilizada no decorrer do programa
 * Usada somente para testes durante o desenvolvimento
 * senha criptografada  com spring security asd = $2a$10$k1SV2r4SH9DcZloBEndktOo2ePCxYeSCOBzpmWuMJg3WHIYRSMZ62 
 */

public class GestaoDeContratos {

	public static void main(String[] args) {
		SetorDAO sdao = new SetorDAO("gestaodecontratos", "douglas", "administrador", "10.95.1.247");
		UsuarioDAO udao = new UsuarioDAO("gestaodecontratos", "douglas", "administrador", "10.95.1.247");
		Setor s;
		ArrayList<Setor> setores = new ArrayList<Setor>();
		
		setores = sdao.getAll();
		
		for (int i = 0 ; i < setores.size(); ++i){
			System.out.println(setores.get(i).toString());
		}
		
	}
}
