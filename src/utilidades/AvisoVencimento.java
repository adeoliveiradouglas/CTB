/*Thread responsável por verificar o banco em busca de contratos com vencimentos menores que 90 dias para avisar os responsáveis
 *por email*/

package utilidades;

import java.util.ArrayList;
import java.util.List;

import dao.ContratoDAO;
import dao.UsuarioDAO;
import entity.Contrato;
import entity.Usuario;

public class AvisoVencimento implements Runnable {
	public AvisoVencimento() {}	
	
	@Override
	public void run(){
		while (true) {
			CalcularData calculadoraData;
			boolean avisado; 
			
			//todos os contratos cadastrados
			List<Contrato> todosOsContratos = new ContratoDAO().getAll();
			
			//lista com os usuários que receberão os emails
			List<Usuario> destinos;
			
			for (Contrato c : todosOsContratos) {
				//calculadora de datas com base no vencimento do contrato
				calculadoraData =  new CalcularData(c.getDataVencimentoContrato());
				avisado = false;
				
				destinos = new ArrayList<Usuario>();
				System.out.println("Analisando contrato " + c.getNumero() + " faltam " + calculadoraData.diasEntre() + " dias para vencer");
				
//				adiciona gestor e fiscal na lista de destinos pois eles sempre são avisados
				destinos.add(c.getFiscal());
				destinos.add(c.getGestor());

//				procura todos os gestores gerais e coloca na lista de destino também
				for (Usuario u : new UsuarioDAO().getAllByCargo("Gestor geral"))
					destinos.add(u);

				
				if(calculadoraData.faltam45dias() && !c.avisado45){
//					se estiver faltando menos de 45 dias, avisar para diretor e presidente	
					for (Usuario u : new UsuarioDAO().getAllByCargo("Diretor"))
						destinos.add(u);

					for (Usuario u : new UsuarioDAO().getAllByCargo("Presidente"))
						destinos.add(u);

//					avisa todos os usuários da lista de destino
					for (Usuario u: destinos)
						new Email().aviso45dias(u.getEmail(), c);
					
//					marca que foi avisado
//					marcar as três pois pode ocorrer de um contrato ser inserido e já ter menos de 90 dias para vencer
					c.setAvisado45(true);
					c.setAvisado60(true);
					avisado = true;
				}
				else if(calculadoraData.faltam60dias() && !c.avisado60){
					for (Usuario u : new UsuarioDAO().getAllByCargo("Diretor"))
						destinos.add(u);

					for (Usuario u: destinos)
						new Email().aviso60dias(u.getEmail(), c);
					
					c.setAvisado60(true);
					avisado = true;
				}
				else if(calculadoraData.faltam90dias() && !c.avisado90){
					for (Usuario u: destinos)
						new Email().aviso90dias(u.getEmail(), c);
					
					avisado = true;
				}
				
				if(avisado){
					c.setAvisado90(true);
					
//					atualiza o banco de dados
					new ContratoDAO().atualizarAvisoDeVencimento(c);
				}
			}
			
			
			try {
//				daqui a tanto tempo, fazer novamente
				Thread.sleep(10800000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
