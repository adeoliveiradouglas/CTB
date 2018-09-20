package main;

import java.util.ArrayList;

import dao.ProcessoDAO;
import entity.Processo;

public class Teste {

	public static void main(String[] args) {
		ProcessoDAO pdao = new ProcessoDAO();
		ArrayList<Processo> processos = pdao.getAllSemPagamento();
		
		for (Processo p: processos) {
			if(Integer.parseInt(p.getAno()) < 2017) {
				pdao.atualizarPagamento(p.getIdContrato(), 15);
			}
		}
	}
}