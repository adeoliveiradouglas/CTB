package main;

import java.util.ArrayList;

import dao.ProcessoDAO;
import entity.Processo;

public class Main {

	public static void main(String[] args) {
		ProcessoDAO pdao = new ProcessoDAO();
		ArrayList<Processo> processos = pdao.getAllSemPagamento();
		
		for (Processo p: processos) {
			System.out.print(p.getMesAsInt()+"/"+p.getAno());
			if((Integer.parseInt(p.getAno()) < 2018) || (Integer.parseInt(p.getMesAsInt()) < 7)) {
				System.out.print(" OK");
				pdao.atualizarPagamento(p.getIdProcesso(), 15);
			}
			System.out.println("");
		}
	}

}
