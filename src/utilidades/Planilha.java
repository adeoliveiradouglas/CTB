package utilidades;


import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import entity.Processo;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


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
		
		Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(planilha);
		} catch (BiffException | IOException e) {
			e.printStackTrace();
			return new ArrayList<Processo>();
		}
		
		Sheet sheet = workbook.getSheet(0);
		int quantLinhas = sheet.getRows();

 		ArrayList<Processo> lp = new ArrayList<>();
		
		for (int i = 1; i <= quantLinhas; ++i){
			//Busca pela primeira linha de processos desse arquivo
			int valor = 0;
			try {
				valor = Integer.parseInt(sheet.getCell(posicaoAno, i).getContents());
			} catch (NumberFormatException e) {}
			
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
						sheet.getCell(posicaoValorAditivo, linhaLeitura).getContents()
					)
				);
			} catch (Exception e) {
//				Se o campo estiver vazio põe zero no lugar
				aditivo = new BigDecimal("0");
			}
			
			try {
				valor = new BigDecimal(
					fc.stringToDecimal(
						sheet.getCell(posicaoValor, linhaLeitura).getContents()
					)
				);
			} catch (Exception e) {
//				Se o campo estiver vazio põe zero no lugar
				valor = new BigDecimal("0");
			}
			
			Date processo;
			
			try {
				processo = ((DateCell) sheet.getCell(posicaoDataProcesso, linhaLeitura)).getDate();
			} catch (Exception e) {
				processo = new Date();
			}
			
			Processo p = new Processo(
				sheet.getCell(posicaoNotaFiscal, linhaLeitura).getContents(), 
				sheet.getCell(posicaoObjeto, linhaLeitura).getContents(), 
				sheet.getCell(posicaoNumeroSei, linhaLeitura).getContents(), 
				sheet.getCell(posicaoAno, linhaLeitura).getContents(), 
				sheet.getCell(posicaoMes, linhaLeitura).getContents(), 
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
