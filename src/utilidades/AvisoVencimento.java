/*Thread responsável por verificar o banco em busca de contratos com vencimentos menores que 90 dias para avisar os responsáveis
 *por email*/

package utilidades;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.Days;

import dao.ContratoDAO;
import dao.UsuarioDAO;
import entity.Contrato;
import entity.Usuario;

public class AvisoVencimento implements Runnable {
	public AvisoVencimento() {}	
	
	@Override
	public void run(){
		while (true) {
			DateTime hoje = new DateTime();
			Days d;

			ArrayList<Contrato> cs = new ContratoDAO().getAll();
			ArrayList<Usuario> destinos;
			for (Contrato c : cs) {
				d = Days.daysBetween(hoje, c.getDataVencimentoContrato());
				destinos = new ArrayList<Usuario>();
				System.out.println("Analisando contrato " + c.getNumero() + " faltam " + d.getDays() + " dias para vencer");
				destinos.add(c.getFiscal());
				destinos.add(c.getGestor());

				for (Usuario u : new UsuarioDAO().getAllByCargo("Gestor geral"))
					destinos.add(u);

				if(d.getDays() <= 45 && !c.avisado45){
					for (Usuario u : new UsuarioDAO().getAllByCargo("Diretor"))
						destinos.add(u);

					for (Usuario u : new UsuarioDAO().getAllByCargo("Presidente"))
						destinos.add(u);

					for (Usuario u: destinos)
						new Email().aviso45dias(u.getEmail());
					
					c.setAvisado45(true);
					c.setAvisado60(true);
					c.setAvisado90(true);
					new ContratoDAO().atualizarAvisoDeVencimento(c);
				}
				else if(d.getDays() <= 60 && !c.avisado60){
					for (Usuario u : new UsuarioDAO().getAllByCargo("Diretor"))
						destinos.add(u);

					for (Usuario u: destinos)
						new Email().aviso60dias(u.getEmail());
					
					c.setAvisado60(true);
					c.setAvisado90(true);
					new ContratoDAO().atualizarAvisoDeVencimento(c);
				}
				else if(d.getDays() <= 90 && !c.avisado90){
					for (Usuario u: destinos)
						new Email().aviso90dias(u.getEmail());
					
					c.setAvisado90(true);
					new ContratoDAO().atualizarAvisoDeVencimento(c);
				}
				
				/*if (!c.isAvisado90() || !c.isAvisado60() || !c.isAvisado45()) {
					switch (d.getDays()) {
					case 45:
						for (Usuario u : new UsuarioDAO().getAllByCargo("Diretor"))
							destinos.add(u);

						for (Usuario u : new UsuarioDAO().getAllByCargo("Presidente"))
							destinos.add(u);

						for (Usuario u: destinos)
							new Email().aviso45dias(u.getEmail());
						
						c.setAvisado45(true);
						c.setAvisado60(true);
						c.setAvisado90(true);
						new ContratoDAO().atualizarAvisoDeVencimento(c);
						break;
					case 60:
						for (Usuario u : new UsuarioDAO().getAllByCargo("Diretor"))
							destinos.add(u);

						for (Usuario u: destinos)
							new Email().aviso45dias(u.getEmail());
						
						c.setAvisado60(true);
						c.setAvisado90(true);
						new ContratoDAO().atualizarAvisoDeVencimento(c);
						break;
					case 90:
						for (Usuario u: destinos)
							new Email().aviso45dias(u.getEmail());
						
						c.setAvisado90(true);
						new ContratoDAO().atualizarAvisoDeVencimento(c);
						break;
					}
				}*/
			}
			try {
//				de 6 em 6 horas: 21600000
				Thread.sleep(10800000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
