package utilidades;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import entity.Contrato;
import entity.Dados;


public class Planilha {
	final int posicaoAno = 0,
			  posicaoMes = 2,
			  posicaoNotaFiscal = 3,
			  posicaoNumeroSei = 4,
			  posicaoDataProcesso = 5,
			  posicaoValor = 6,
			  posicaoValorAditivo = 9,
			  posicaoObjeto = 10,
			  posicaoSaldo = 7;
	
	public Planilha(){}
	
	public List<Dados> carregarXLSX(File planilha, Contrato contrato){
		
		int mesesConsecutivosSemDados = 0, //se esse valor chegar a 12 (1 ano) o loop para
			linhaLeitura = 0;
		
		XSSFWorkbook workbook = null;
		FileInputStream fisPlanilha = null;
		
		try {
//			Cria objeto de input stream
			fisPlanilha = new FileInputStream(planilha);
			
//			cria workbook
			workbook = new XSSFWorkbook(fisPlanilha);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Dados>();
		} 
		
//		Recupera a primeira aba (Plan1) da planilha e põe em sheet
		XSSFSheet sheet = workbook.getSheetAt(0);
		int quantLinhas = sheet.getPhysicalNumberOfRows();
		
 		List<Dados> lp = new ArrayList<Dados>();
		
		for (int i = 1; i <= quantLinhas; ++i){
			//Busca pela primeira linha de processos desse arquivo
			int valor = 0;
			
			try {
				valor = (int) sheet.getRow(i).getCell(posicaoAno).getNumericCellValue();
//				System.out.println(valor);
			} catch (NumberFormatException | IllegalStateException e) {}
			
			if(valor > 2000){
				linhaLeitura = i;
				i += quantLinhas;
			}
		}
		
		while (linhaLeitura < quantLinhas && mesesConsecutivosSemDados <= 12){			
			BigDecimal aditivo = null, valor = null, saldo = null;
			Date processo;
			sheet.getRow(linhaLeitura).getCell(posicaoObjeto).setCellType(1);
			String notaFiscal = "",
					   objeto = sheet.getRow(linhaLeitura).getCell(posicaoObjeto).getStringCellValue(),
					   numeroSei = "",
					   mes = sheet.getRow(linhaLeitura).getCell(posicaoMes).getStringCellValue();
			int ano = (int) sheet.getRow(linhaLeitura).getCell(posicaoAno).getNumericCellValue();
				
				
			try {
//				System.out.println(sheet.getRow(linhaLeitura).getCell(posicaoValorAditivo).getNumericCellValue());
				aditivo = new BigDecimal(
					"" + sheet.getRow(linhaLeitura).getCell(posicaoValorAditivo).getNumericCellValue()
				);
			} catch (Exception e) {
//				Se o campo estiver vazio põe zero no lugar
				aditivo = new BigDecimal("0");
			}
			
			try {
//				System.out.println(sheet.getRow(linhaLeitura).getCell(posicaoValor).getNumericCellValue());
				valor = new BigDecimal(
					"" + sheet.getRow(linhaLeitura).getCell(posicaoValor).getNumericCellValue()
				);
			} catch (Exception e) {
//				Se o campo estiver vazio põe zero no lugar
				valor = new BigDecimal("0");
			}
			
			try {
//				System.out.println(sheet.getRow(linhaLeitura).getCell(posicaoValor).getNumericCellValue());
				saldo = new BigDecimal(
					"" + sheet.getRow(linhaLeitura).getCell(posicaoSaldo).getNumericCellValue()
				);
			} catch (Exception e) {
//				Se o campo estiver vazio põe zero no lugar
				saldo = new BigDecimal("0");
			}
			
			try {
				processo = sheet.getRow(linhaLeitura).getCell(posicaoDataProcesso).getDateCellValue();
			} catch (Exception e) {
				processo = new Date();
			}

			/*
			 * Por algum motivo, o processamento gerava uma exception de que não podia ler um número de um campo de texto
			 * ou um texto em campo de número. Try catch para os atributos que estavam gerando as exceções e tenta ler de
			 * outra forma caso a primeira dê erro.
			 */			
			try {
				notaFiscal = "" + (int) sheet.getRow(linhaLeitura).getCell(posicaoNotaFiscal).getNumericCellValue();
			} catch (IllegalStateException e){
				notaFiscal = "" + sheet.getRow(linhaLeitura).getCell(posicaoNotaFiscal).toString();
			}
			
			try {
				numeroSei = "" + (int) sheet.getRow(linhaLeitura).getCell(posicaoNumeroSei).getNumericCellValue();
			} catch (IllegalStateException e) {
				numeroSei = sheet.getRow(linhaLeitura).getCell(posicaoNumeroSei).getStringCellValue();
			}
			
			if (notaFiscal.equals("0"))
				notaFiscal = "";
			
			if (numeroSei.equals("0"))
				numeroSei = "";
			
			saldo = contrato.getSaldo().add(aditivo);
			saldo = saldo.subtract(valor);
			
			Dados p = new Dados(
				notaFiscal,
				objeto, 
				numeroSei,
				ano, 
				mes,
				aditivo, 
				valor, 
				saldo,
				processo, 
				contrato.getId()
			);

			// linhas vazias ou inválidas do arquivo não são inseridas
			if (!p.getNotaFiscal().equals("") || !p.getTipoAditivo().equals("") || !p.getNumeroSei().equals("")
					|| !p.getAditivoAsString().equals("0,00")) {
				lp.add(p);
				mesesConsecutivosSemDados = 0;
			} else {
				++mesesConsecutivosSemDados;
			}

			++linhaLeitura;
		}
		
		return lp;
	}

	public List<Dados> carregarXLS(File planilha, Contrato contrato) {
		int mesesConsecutivosSemDados = 0, //se esse valor chegar a 12 (1 ano) o loop para
				linhaLeitura = 0;
			
		HSSFWorkbook workbook = null;
		FileInputStream fisPlanilha = null;
		
		try {
//				Cria objeto de input stream
			fisPlanilha = new FileInputStream(planilha);
			
//				cria workbook
			workbook = new HSSFWorkbook(fisPlanilha);
		} catch (FileNotFoundException e){
			e.printStackTrace();
			return new ArrayList<Dados>();
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<Dados>();
		} 
		
//			Recupera a primeira aba (Plan1) da planilha e põe em sheet
		HSSFSheet sheet = workbook.getSheetAt(0);
		int quantLinhas = sheet.getPhysicalNumberOfRows();
		
 		List<Dados> lp = new ArrayList<Dados>();
		
		for (int i = 1; i <= quantLinhas; ++i){
			//Busca pela primeira linha de processos desse arquivo
			int valor = 0;
			
			try {
				valor = (int) sheet.getRow(i).getCell(posicaoAno).getNumericCellValue();
//					System.out.println(valor);
			} catch (NumberFormatException | IllegalStateException e) {}
			
			if(valor > 2000){
				linhaLeitura = i;
				i += quantLinhas;
			}
		}
		
		BigDecimal saldo = contrato.getSaldo();
		while (linhaLeitura < quantLinhas && mesesConsecutivosSemDados <= 12){			
			BigDecimal aditivo = null, valor = null;
			Date processo;
			String notaFiscal = "",
					   objeto = sheet.getRow(linhaLeitura).getCell(posicaoObjeto).getStringCellValue(),
					   numeroSei = "",
					   mes = sheet.getRow(linhaLeitura).getCell(posicaoMes).getStringCellValue();
			int ano = (int) sheet.getRow(linhaLeitura).getCell(posicaoAno).getNumericCellValue();
				
			try {
				aditivo = new BigDecimal(
					"" + sheet.getRow(linhaLeitura).getCell(posicaoValorAditivo).getNumericCellValue()
				);
			} catch (Exception e) {
//					Se o campo estiver vazio põe zero no lugar
				aditivo = new BigDecimal("0");
			}
			
			try {
//					System.out.println(sheet.getRow(linhaLeitura).getCell(posicaoValor).getNumericCellValue());
				valor = new BigDecimal(
					"" + sheet.getRow(linhaLeitura).getCell(posicaoValor).getNumericCellValue()
				);
			} catch (Exception e) {
//					Se o campo estiver vazio põe zero no lugar
				valor = new BigDecimal("0");
			}
			
			try {
				processo = sheet.getRow(linhaLeitura).getCell(posicaoDataProcesso).getDateCellValue();
			} catch (Exception e) {
				processo = new Date();
			}
			
			/*
			 * Por algum motivo, o processamento gerava uma exception de que não podia ler um número de um campo de texto
			 * ou um texto em campo de número. Try catch para os atributos que estavam gerando as exceções e tenta ler de
			 * outra forma caso a primeira dê erro.
			 */			
			try {
				notaFiscal = "" + (int) sheet.getRow(linhaLeitura).getCell(posicaoNotaFiscal).getNumericCellValue();
			} catch (IllegalStateException e){
				notaFiscal = "" + sheet.getRow(linhaLeitura).getCell(posicaoNotaFiscal).toString();
			}
			
			try {
				numeroSei = "" + (int) sheet.getRow(linhaLeitura).getCell(posicaoNumeroSei).getNumericCellValue();
			} catch (IllegalStateException e) {
				numeroSei = sheet.getRow(linhaLeitura).getCell(posicaoNumeroSei).getStringCellValue();
			}
			
			if (notaFiscal.equals("0"))
				notaFiscal = "";
			
			if (numeroSei.equals("0"))
				numeroSei = "";
			
			saldo = saldo.add(aditivo);
			saldo = saldo.subtract(valor);		
			
			Dados p = new Dados(
				notaFiscal,
				objeto, 
				numeroSei,
				ano, 
				mes,
				aditivo, 
				valor, 
				saldo,
				processo, 
				contrato.getId()
			);
			
//				linhas vazias do arquivo não são inseridas
			if (!p.getNotaFiscal().equals("") || !p.getTipoAditivo().equals("") || !p.getNumeroSei().equals("")
					|| !p.getAditivoAsString().equals("0,0")) {
				lp.add(p);
				mesesConsecutivosSemDados = 0;
			} else {
				++mesesConsecutivosSemDados;
			}
			
			++linhaLeitura;
		}
		
		return lp;
	}
}
