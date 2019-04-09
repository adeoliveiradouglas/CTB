package utilidades;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import org.joda.time.DateTime;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import entity.Contrato;
import entity.Dados;

@SuppressWarnings("unused")
public class PDF {
	/*
	 * pastaRaiz do contexto do tomcat em formato de string para que depois o cliente
	 * consiga abrir através de um link na página
	 */
	private final String subPasta = "Comum\\relatorios\\";
	private String pastaRaiz, nomeArquivo;
	private Document d = new Document(PageSize.A4.rotate());
	private Font f = new Font();
	private Paragraph paragrafo;
	private DateTime hoje = new DateTime();
	private Image logoCtbPequeno, logoPequeno;

	public PDF(String pastaRaiz) {
		this.pastaRaiz = pastaRaiz;
		f.setSize(10);
	}

	public void relatorioContrato(Contrato contrato) {
		try {
			iniciarArquivo("relatorioContrato" + contrato.getId() + ".pdf");
			System.out.println(nomeArquivo);
			
			PdfPTable tabela = new PdfPTable(4);
			tabela.setWidthPercentage(100.0f);
			PdfPCell celula;
			
			d.add(logoCtbPequeno);
			d.add(logoPequeno);
			
			paragrafo = new Paragraph("SGC - RELATÓRIO DE CONTRATO\n"+contrato.getNome()+"\n\n\n");
			paragrafo.setAlignment(Element.ALIGN_CENTER);
			d.add(paragrafo);
			
			tabela.addCell(celulaSemBorda("Nº contrato:"+contrato.getNumero()));
			tabela.addCell(celulaSemBorda("Nº portaria:"+contrato.getPortaria()));
			tabela.addCell(celulaSemBorda("Gestor:"+contrato.getGestor().getNome()));
			tabela.addCell(celulaSemBorda("Fiscal: "+contrato.getFiscal().getNome()));
			tabela.addCell(celulaSemBorda("Recurso: "+contrato.getRecurso().getNome()));
			tabela.addCell(celulaSemBorda("Uso: "+contrato.getUso().getNome()));
			tabela.addCell(celulaSemBorda("Fonte pagante: "+contrato.getFontePagante().getNome()));
			tabela.addCell(celulaSemBorda(""));
			tabela.addCell(celulaSemBorda("Valor inicial: R$"+contrato.getValorInicialAsString()));
			tabela.addCell(celulaSemBorda("Total dos aditivos: R$"+contrato.getValorAditivoAsString()));
			tabela.addCell(celulaSemBorda("Valor total: R$"+contrato.getValorTotalAsString()));
			tabela.addCell(celulaSemBorda(""));
			tabela.addCell(celulaSemBorda("Ass.: "+contrato.getDataAssinaturaAsString()));
			tabela.addCell(celulaSemBorda("Venc.: "+contrato.getDataVencimentoContratoAsString()));
			tabela.addCell(celulaSemBorda("Ass. da garantia: "+contrato.getDataGarantiaAsString()));
			tabela.addCell(celulaSemBorda("Venc. da garantia: "+contrato.getDataVencimentoGarantiaAsString()));

			d.add(tabela);
			
			d.add(new Paragraph("Objeto: "+contrato.getObjeto()));
			d.add(new Paragraph("\n\n"));
			
			//cada um desses valores corresponde a largura de cada célula
			tabela = new PdfPTable(new float[] {0.035f, 0.065f, 0.1f,0.1f,0.065f,0.1f,0.1f,0.1f,0.1f,0.065f });
			tabela.setHorizontalAlignment(Element.ALIGN_CENTER);
			tabela.setWidthPercentage(100.0f);
			tabela.addCell(celula(frase("Med.")));
			tabela.addCell(celula(frase("Referência")));
			tabela.addCell(celula(frase("Nº")));
			tabela.addCell(celula(frase("Nota Fiscal")));
			tabela.addCell(celula(frase("Data")));
			tabela.addCell(celula(frase("Valor")));
			tabela.addCell(celula(frase("Saldo")));
			tabela.addCell(celula(frase("Aditivo")));
			tabela.addCell(celula(frase("Observação")));
			tabela.addCell(celula(frase("Pagamento")));
			
			int i = contrato.getDados().size();
			for (Dados data: contrato.getDados()) {
				tabela.addCell(celula(frase(""+i)));
				tabela.addCell(celula(frase(data.getMesAsInt()+"/"+data.getAno())));
				tabela.addCell(celula(frase(data.getNumeroSei())));
				tabela.addCell(celula(frase(data.getNotaFiscal())));
				tabela.addCell(celula(frase(data.getDataAsString())));
				tabela.addCell(celula(frase("R$"+data.getValorAsString())));
				tabela.addCell(celula(frase("R$"+data.getSaldoAsString())));
				tabela.addCell(celula(frase("R$"+data.getAditivoAsString())));
				tabela.addCell(celula(frase(data.getTipoAditivo())));
				tabela.addCell(celula(frase(data.getDataPagamentoAsString())));
				
				--i;
			}

			d.add(tabela);
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		} finally {
			fecharArquivo();
		}
	}

	private void fecharArquivo() {
		d.close();

		/* descomente esse trecho para o arquivo abrir no SERVIDOR quando for gerado */
//		try {
//			File arquivo = new File(nomeArquivo);
//			Desktop.getDesktop().open(arquivo);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	};

	private void iniciarArquivo(String string) throws DocumentException, MalformedURLException, IOException {
		nomeArquivo = pastaRaiz + subPasta  + string;

		new File(pastaRaiz + subPasta).mkdirs();
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream(nomeArquivo);
		} catch (FileNotFoundException e) {
			Runtime.getRuntime().exec("taskkill /F /IM AcroRd32.exe");
			
			fos = new FileOutputStream(nomeArquivo);
		}
		
		PdfWriter.getInstance(d, fos);
		d.open();

		logoCtbPequeno = Image.getInstance(pastaRaiz + "layout\\images\\logo-ctb pequeno.png");
		logoPequeno = Image.getInstance(pastaRaiz + "layout\\images\\logo pequeno.png");
		int yImagePosition = 525;
		logoCtbPequeno.setAbsolutePosition(35, yImagePosition);
		logoPequeno.setAbsolutePosition(750, yImagePosition);

	}
	
	private Phrase frase(String texto) {
		Phrase frase = new Phrase();
		frase.setFont(f);
		frase.add(texto);
		
		return frase;
	}
	
	private PdfPCell celula(Phrase texto) {
		PdfPCell celula = new PdfPCell(texto);
		celula.setHorizontalAlignment(Element.ALIGN_CENTER);
		
		return celula;
	}
	private PdfPCell celulaSemBorda(String texto) {
		
		PdfPCell celula = new PdfPCell(new Phrase(texto));
		celula.setBorder(Rectangle.NO_BORDER);
		
		return celula;
	}
}