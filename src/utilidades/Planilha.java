package utilidades;


import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import entity.Processo;
import jxl.Cell;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class Planilha {
	final int posicaoNotaFiscal = 3,
			  posicaoNumeroSei = 4,
			  posicaoAno = 0,
			  posicaoMes = 2,
			  posicaoValor = 6,
			  posicaoValorAditivo = 9,
			  posicaoObjeto = 10,
			  posicaoDataProcesso = 5;
	
	public Planilha(){}
	
	public ArrayList<Processo> carregar(File planilha, int contrato){
		int mesesConsecutivosSemDados = 0, //se esse valor chegar a 12 (1 ano) o loop para
			i = 14; //primeira linha de dados da planilha
		
		FormatarCampo fc = new FormatarCampo();
		
		Workbook workbook = null;
		try {
			workbook = Workbook.getWorkbook(planilha);
		} catch (BiffException | IOException e) {
			return null;
		}
		
		Sheet sheet = workbook.getSheet(0);
		int linhas = sheet.getRows();

		ArrayList<Processo> lp = new ArrayList<>();
		
		while (i < linhas && mesesConsecutivosSemDados <= 12){			
			BigDecimal aditivo = null, valor = null;
			
			try {
				aditivo = new BigDecimal(
					fc.stringToDecimal(
						sheet.getCell(posicaoValorAditivo, i).getContents()
					)
				);
			} catch (Exception e) {
//				Se o campo estiver vazio põe zero no lugar
				aditivo = new BigDecimal("0");
			}
			
			try {
				valor = new BigDecimal(
					fc.stringToDecimal(
						sheet.getCell(posicaoValor, i).getContents()
					)
				);
			} catch (Exception e) {
//				Se o campo estiver vazio põe zero no lugar
				valor = new BigDecimal("0");
			}
			
			Date processo;
			
			try {
				processo = ((DateCell) sheet.getCell(posicaoDataProcesso, i)).getDate();
			} catch (Exception e) {
				processo = new Date();
			}
			
			Processo p = new Processo(
				sheet.getCell(posicaoNotaFiscal, i).getContents(), 
				sheet.getCell(posicaoObjeto, i).getContents(), 
				sheet.getCell(posicaoNumeroSei, i).getContents(), 
				sheet.getCell(posicaoAno, i).getContents(), 
				sheet.getCell(posicaoMes, i).getContents(), 
				aditivo, 
				valor, 
				processo, 
				contrato
			);
			
			++i;
			
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
