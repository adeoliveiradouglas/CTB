import java.util.Random;

import utilidades.Email;

/*
 * Classe main não utilizada no decorrer do programa
 * Usada somente para testes durante o desenvolvimento
 */

public class GestaoDeContratos {

	public static void main(String[] args) {
		int token = new Random().nextInt(8999) + 1000;
		
		new Email().enviarCodigo(
				"adeoliveiradouglas@gmail.com", 
				token
			);
	}
	
}
