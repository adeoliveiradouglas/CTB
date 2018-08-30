package main;

import java.util.ArrayList;

import dao.ContratoDAO;
import entity.Contrato;

public class Teste {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		ArrayList<Contrato> clist = new ContratoDAO().getAll();

	}

}
