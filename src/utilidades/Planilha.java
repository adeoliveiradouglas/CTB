package utilidades;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import entity.Processo;


public class Planilha {
	final int posicaoAno = 0,
			  posicaoMes = 2,
			  posicaoNotaFiscal = 3,
			  posicaoNumeroSei = 4,
			  posicaoDataProcesso = 5,
			  posicaoValor = 6,
			  posicaoValorAditivo = 9,
			  posicaoObjeto = 10;
	
	public Planilha(){}
	
	public ArrayList<Processo> carregarXLSX(File planilha, int contrato){
		
		int mesesConsecutivosSemDados = 0, //se esse valor chegar a 12 (1 ano) o loop para
			linhaLeitura = 0;
		
		XSSFWorkbook workbook = null;
		FileInputStream fisPlanilha = null;
		
		try {
//			Cria objeto de input stream
			fisPlanilha = new FileInputStream(planilha);
			
//			cria workbook
			workbook = new XSSFWorkbook(fisPlanilha);
		} catch (FileNotFoundException e){
			e.printStackTrace();
			return new ArrayList<Processo>();
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<Processo>();
		} 
		
//		Recupera a primeira aba (Plan1) da planilha e põe em sheet
		XSSFSheet sheet = workbook.getSheetAt(0);
		int quantLinhas = sheet.getPhysicalNumberOfRows();
		
 		ArrayList<Processo> lp = new ArrayList<>();
		
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
		
//		linhaLeitura = 71;
		
		while (linhaLeitura < quantLinhas && mesesConsecutivosSemDados <= 12){			
//			System.out.println(linhaLeitura);
			BigDecimal aditivo = null, valor = null;
			
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
			
			Date processo;
			
			try {
				processo = sheet.getRow(linhaLeitura).getCell(posicaoDataProcesso).getDateCellValue();
			} catch (Exception e) {
				processo = new Date();
			}
			
//			System.out.println(sheet.getRow(linhaLeitura).getCell(posicaoNotaFiscal).getStringCellValue());
			
			
			String notaFiscal = "",
				   objeto = sheet.getRow(linhaLeitura).getCell(posicaoObjeto).getStringCellValue(),
				   numeroSei = "",
				   ano = "" + (int) sheet.getRow(linhaLeitura).getCell(posicaoAno).getNumericCellValue(),
				   mes = sheet.getRow(linhaLeitura).getCell(posicaoMes).getStringCellValue();
			
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
			
			Processo p = new Processo(
				notaFiscal,
				objeto, 
				numeroSei,
				ano, 
				mes,
				aditivo, 
				valor, 
				processo, 
				contrato
			);
			
//			linhas vazias do arquivo não são inseridas
			if((!p.getNotaFiscal().equals("") || !p.getTipoAditivo().equals("") || !p.getNumeroSei().equals(""))){
				lp.add(p);
				mesesConsecutivosSemDados = 0;
			} else {
				++mesesConsecutivosSemDados;
			}
			
			++linhaLeitura;
		}
		
		return lp;
	}

	public ArrayList<Processo> carregarXLS(File planilha, int idContrato) {
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
				return new ArrayList<Processo>();
			} catch (IOException e) {
				e.printStackTrace();
				return new ArrayList<Processo>();
			} 
			
//			Recupera a primeira aba (Plan1) da planilha e põe em sheet
			HSSFSheet sheet = workbook.getSheetAt(0);
			int quantLinhas = sheet.getPhysicalNumberOfRows();
			
	 		ArrayList<Processo> lp = new ArrayList<>();
			
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
			
//			linhaLeitura = 71;
			
			while (linhaLeitura < quantLinhas && mesesConsecutivosSemDados <= 12){			
//				System.out.println(linhaLeitura);
				BigDecimal aditivo = null, valor = null;
				
				try {
//					System.out.println(sheet.getRow(linhaLeitura).getCell(posicaoValorAditivo).getNumericCellValue());
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
				
				Date processo;
				
				try {
					processo = sheet.getRow(linhaLeitura).getCell(posicaoDataProcesso).getDateCellValue();
				} catch (Exception e) {
					processo = new Date();
				}
				
//				System.out.println(sheet.getRow(linhaLeitura).getCell(posicaoNotaFiscal).getStringCellValue());
				
				
				String notaFiscal = "",
					   objeto = sheet.getRow(linhaLeitura).getCell(posicaoObjeto).getStringCellValue(),
					   numeroSei = "",
					   ano = "" + (int) sheet.getRow(linhaLeitura).getCell(posicaoAno).getNumericCellValue(),
					   mes = sheet.getRow(linhaLeitura).getCell(posicaoMes).getStringCellValue();
				
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
				
				Processo p = new Processo(
					notaFiscal,
					objeto, 
					numeroSei,
					ano, 
					mes,
					aditivo, 
					valor, 
					processo, 
					idContrato
				);
				
//				linhas vazias do arquivo não são inseridas
				if((!p.getNotaFiscal().equals("") || !p.getTipoAditivo().equals("") || !p.getNumeroSei().equals(""))){
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
