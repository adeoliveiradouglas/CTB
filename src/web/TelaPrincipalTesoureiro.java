package web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dao.ContratoDAO;
import dao.ProcessoDAO;
import entity.Contrato;
import entity.Processo;

public class TelaPrincipalTesoureiro implements Logica{

	@Override
	public String executa(HttpServletRequest pedido) throws Exception {
		ProcessoDAO pdao = new ProcessoDAO();
		ArrayList<Processo> processosSemPagamento = pdao.getAllSemPagamento();
		ArrayList<Integer> carregados = new ArrayList<Integer>();

//		Vai procurar por todos os contratos que tem processos sem pagamento
		for (Processo p: processosSemPagamento){
			int idContrato = p.getIdContrato();
			
//			se o contrato ainda não está na lista, será adicionado
			if(carregados.indexOf(idContrato) == -1){
				carregados.add(idContrato);
			}
		}
		
		ArrayList<Contrato> contratosSemPagamento = new ArrayList<Contrato>();
		ContratoDAO cdao = new ContratoDAO();
		
		for (int id: carregados){
			contratosSemPagamento.add(cdao.getByIdSemPagamento(id));
		}
		
		pedido.getSession().setAttribute("contratosSemPagamento", contratosSemPagamento);
		
		return "/Tesoureiro/index.jsp";
	}

}
