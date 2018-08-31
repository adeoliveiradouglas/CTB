package utilidades;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

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
	
	public ArrayList<Processo> carregar(File planilha, int contrato){
		
		int mesesConsecutivosSemDados = 0, //se esse valor chegar a 12 (1 ano) o loop para
			linhaLeitura = 0;
		
		FormatarCampo fc = new FormatarCampo();
		
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
			} catch (NumberFormatException | IllegalStateException e) {}
			
			if(valor > 2000){
				linhaLeitura = i;
				i += quantLinhas;
			}
		}
		
		while (linhaLeitura < quantLinhas && mesesConsecutivosSemDados <= 12){			
			BigDecimal aditivo = null, valor = null;
			
			try {
				aditivo = new BigDecimal(
					fc.stringToDecimal(
						sheet.getRow(linhaLeitura).getCell(posicaoValorAditivo).toString()
					)
				);
			} catch (Exception e) {
//				Se o campo estiver vazio põe zero no lugar
				aditivo = new BigDecimal("0");
			}
			
			try {
				valor = new BigDecimal(
					fc.stringToDecimal(
						sheet.getRow(linhaLeitura).getCell(posicaoValor).toString()
					)
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
			
			Processo p = new Processo(
				sheet.getRow(linhaLeitura).getCell(posicaoNotaFiscal).toString(), 
				sheet.getRow(linhaLeitura).getCell(posicaoObjeto).toString(), 
				sheet.getRow(linhaLeitura).getCell(posicaoNumeroSei).toString(), 
				"" + sheet.getRow(linhaLeitura).getCell(posicaoAno).getNumericCellValue(), 
				sheet.getRow(linhaLeitura).getCell(posicaoMes).toString(), 
				aditivo, 
				valor, 
				processo, 
				contrato
			);
			
			++linhaLeitura;
			
//			linhas vazias do arquivo não são inseridas
			if((!p.getNotaFiscal().equals("") || !p.getTipoAditivo().equals("") || !p.getNumeroSei().equals("")) && !p.getTipoAditivo().equals("#REF!")){
				lp.add(p);
				mesesConsecutivosSemDados = 0;
			} else {
				++mesesConsecutivosSemDados;
			}
		}
		
		return lp;
	}
}
