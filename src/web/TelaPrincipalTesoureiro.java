package web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContratoDAO;
import dao.ProcessoDAO;
import entity.Contrato;
import entity.Processo;
import utilidades.ComparadorNome;

public class TelaPrincipalTesoureiro implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		ProcessoDAO pdao = new ProcessoDAO();
		List<Processo> processosSemPagamento = pdao.getAllSemPagamento();
		List<Integer> carregados = new ArrayList<Integer>();

//		Vai procurar por todos os contratos que tem processos sem pagamento
		for (Processo p: processosSemPagamento){
			int idContrato = p.getIdContrato();
			
//			se o contrato ainda não está na lista, será adicionado
			if(carregados.indexOf(idContrato) == -1){
				carregados.add(idContrato);
			}
		}
		
		List<Contrato> contratosSemPagamento = new ArrayList<Contrato>();
		ContratoDAO cdao = new ContratoDAO();
		
		for (int id: carregados){
			contratosSemPagamento.add(cdao.getByIdSemPagamento(id));
		}
		
		//organiza a lista pelo nome dos contratos
		Collections.sort(contratosSemPagamento, new ComparadorNome());
		pedido.getSession().setAttribute("contratosSemPagamento", contratosSemPagamento);
		
		return "/Tesoureiro/index.jsp";
	}

}
