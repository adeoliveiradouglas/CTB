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
	private final String subPasta = "Comum\\comprovantes\\";
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

			PdfPTable tabela = new PdfPTable(2);
			tabela.setWidthPercentage(100.0f);
			PdfPCell celula;
			
			d.add(logoCtbPequeno);
			d.add(logoPequeno);
			
			paragrafo = new Paragraph("SGC - RELATÓRIO DE CONTRATO\n"+contrato.getNome()+"\n\n\n");
			paragrafo.setAlignment(Element.ALIGN_CENTER);
			d.add(paragrafo);
			
			tabela.addCell(celulaSemBorda("Número do contrato:"+contrato.getNumero()+"\n"));
			tabela.addCell(celulaSemBorda("Número da portaria:"+contrato.getPortaria()+"\n"));
				
			tabela.addCell(celulaSemBorda("Gestor:"+contrato.getGestor().getNome()+"\n"));
			tabela.addCell(celulaSemBorda("Fiscal: "+contrato.getFiscal().getNome()+"\n"));
			
			d.add(tabela);
			
			tabela = new PdfPTable(3);
			tabela.setWidthPercentage(100.0f);
			
			tabela.addCell(celulaSemBorda("Recurso: "+contrato.getRecurso().getNome()+"\n"));
			tabela.addCell(celulaSemBorda("Uso: "+contrato.getUso().getNome()+"\n"));
			tabela.addCell(celulaSemBorda("Fonte pagante: "+contrato.getFontePagante().getNome()+"\n"));
			
			d.add(tabela);
			
			tabela = new PdfPTable(4);
			tabela.setWidthPercentage(100.0f);
			
			tabela.addCell(celulaSemBorda("Valor inicial: R$"+contrato.getValorInicialAsString()+"\n"));
			tabela.addCell(celulaSemBorda("Total dos aditivos: R$"+contrato.getValorAditivoAsString()+"\n"));
			tabela.addCell(celulaSemBorda("Valor total: R$"+contrato.getValorTotalAsString()+"\n"));
			tabela.addCell(celulaSemBorda("Valor total: R$"+contrato.getSaldoAsString()+"\n"));
			
			tabela = new PdfPTable(4);
			t
			tabela.addCell(celulaSemBorda("Ass.: "+contrato.getDataAssinaturaAsString()+"\n"));
			tabela.addCell(celulaSemBorda("Vencimento: "+contrato.getDataVencimentoContratoAsString()+"\n"));
			tabela.addCell(celulaSemBorda("Ass. da garantia: "+contrato.getDataGarantiaAsString()+"\n"));
//			celula = new PdfPCell(new Phrase(contrato.getDataVencimentoContratoAsString()));
//			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
//			tabela.addCell(celula);

			tabela.addCell(celulaSemBorda("Vencimento da garantia: "+contrato.getDataVencimentoGarantiaAsString()+"\n"));
//			celula = new PdfPCell(new Phrase(contrato.getDataVencimentoGarantiaAsString()));
//			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
//			tabela.addCell(celula);	
			
			tabela.addCell(celulaSemBorda("Objeto: "+contrato.getObjeto()));
			d.add(tabela);
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
//				frase = new Phrase();
//				frase.setFont(f);
//				frase.add(Integer.toString(i));
//				celula = new PdfPCell(frase);
//				celula.setHorizontalAlignment(Element.ALIGN_CENTER);
//				tabela.addCell(celula);
//				--i;
//				
//				frase = new Phrase();
//				frase.setFont(f);
//				frase.add(data.getMes()+"/"+data.getAno());
//				celula = new PdfPCell(frase);
//				celula.setHorizontalAlignment(Element.ALIGN_CENTER);
//				tabela.addCell(celula);
//				
//				frase = new Phrase();
//				frase.setFont(f);
//				frase.add(data.getNumeroSei());
//				celula = new PdfPCell(frase);
//				celula.setHorizontalAlignment(Element.ALIGN_CENTER);
//				tabela.addCell(celula);
//				
//				frase = new Phrase();
//				frase.setFont(f);
//				frase.add(data.getNotaFiscal());
//				tabela.addCell(novaCelula(frase));
//				
//				frase = new Phrase();
//				frase.setFont(f);
//				frase.add("R$"+data.getValorAsString());
//				celula = new PdfPCell(frase);
//				celula.setHorizontalAlignment(Element.ALIGN_CENTER);
//				tabela.addCell(celula);
//				
//				frase = new Phrase();
//				frase.setFont(f);
//				frase.add("R$"+data.getSaldoAsString());
//				celula = new PdfPCell(frase);
//				celula.setHorizontalAlignment(Element.ALIGN_CENTER);
//				tabela.addCell(celula);
//				
//				frase = new Phrase();
//				frase.setFont(f);
//				frase.add("R$"+data.getAditivoAsString());
//				celula = new PdfPCell(frase);
//				celula.setHorizontalAlignment(Element.ALIGN_CENTER);
//				tabela.addCell(celula);
//				
//				frase = new Phrase();
//				frase.setFont(f);
//				frase.add(data.getTipoAditivo());
//				celula = new PdfPCell(frase);
//				celula.setHorizontalAlignment(Element.ALIGN_CENTER);
//				tabela.addCell(celula);
//				
//				frase = new Phrase();
//				frase.setFont(f);
//				frase.add(data.getDataPagamentoAsString());
//				celula = new PdfPCell(frase);
//				celula.setHorizontalAlignment(Element.ALIGN_CENTER);
//				tabela.addCell(celula);
			}

			d.add(tabela);
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		} finally {
			fecharArquivo();
		}
	}
//	public void relatorioTurno(Turno t) {
//		try {
//			iniciarArquivo("RelatorioTurno"+t.getId()+".pdf");
//			
//			d.add(logoPequeno);
//			
//			String ano = (""+hoje.getYear()).substring(2);
//			paragrafo = new Paragraph("RELATÓRIO DO TURNO DE VENDA Nº:A/"+ano+"/"+t.getId()+"\n\n");
//			paragrafo.setAlignment(Element.ALIGN_CENTER);
//			d.add(paragrafo);
//			
//			PdfPTable tabela = new PdfPTable(4);
//			
//			PdfPCell celula = new PdfPCell(new Phrase("Dia"));
//			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
//			tabela.addCell(celula);
//			
//			celula = new PdfPCell(new Phrase("Turno"));
//			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
//			tabela.addCell(celula);
//			
//			celula = new PdfPCell(new Phrase("Recebidos"));
//			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
//			tabela.addCell(celula);
//			
//			celula = new PdfPCell(new Phrase("Devolvidos"));
//			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
//			tabela.addCell(celula);						
//			
//			String data = t.getData().getDayOfMonth() + "/" + t.getData().getMonthOfYear() + "/" + t.getData().getYear();
//			celula = new PdfPCell(new Phrase(data)); 
//			tabela.addCell(celula);
//			
//			celula = new PdfPCell(new Phrase(t.getTurno()));
//			tabela.addCell(celula);
//			
//			celula = new PdfPCell(new Phrase(""+t.getQuantValesRecebidos()));
//			tabela.addCell(celula);
//			
//			celula = new PdfPCell(new Phrase(""+t.getQuantVales()));
//			tabela.addCell(celula);
//			
//			d.add(tabela);
//			
//			paragrafo = new Paragraph("\n");
//			paragrafo.setAlignment(Element.ALIGN_CENTER);
//			
//			d.add(paragrafo);
//			
//			tabela = new PdfPTable(2);
//			
//			celula = new PdfPCell(new Phrase("Total arrecadado: R$"+t.valorArrecadadoAsString()));
//			tabela.addCell(celula);
//			
//			celula = new PdfPCell(new Phrase("Total vendido: "+t.quantidadeVendido()));
//			celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
//			tabela.addCell(celula);
//			
//			d.add(tabela);
//			
//			paragrafo = new Paragraph("\nVendas nesse turno\n\n");
//			paragrafo.setAlignment(Element.ALIGN_CENTER);
//			
//			d.add(paragrafo);
//			
//			tabela = new PdfPTable(2);
//			
//			Font f2 = new Font();
//			f2.setStyle(Font.BOLD);
//			
//			Phrase p = new Phrase();
//			p.setFont(f2);
//			p.add("Nome");
//			celula = new PdfPCell(p);
//			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
//			tabela.addCell(celula);
//			
//			p = new Phrase();
//			p.setFont(f2);
//			p.add("Quantidade");
//			celula = new PdfPCell(p);
//			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
//			tabela.addCell(celula);
//			
//			for(Venda v : t.getVendas()) {
//				celula = new PdfPCell(new Phrase(v.getAluno().getNome()));
//				tabela.addCell(celula);
//				
//				celula = new PdfPCell(new Phrase("" +v.getQuantidade()));
//				celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
//				tabela.addCell(celula);
//			}
//			
//			d.add(tabela);
//			
//			paragrafo = new Paragraph("\n");
//			paragrafo.setAlignment(Element.ALIGN_CENTER);
//			
//			d.add(paragrafo);
//			
//			tabela = new PdfPTable(2);
//			
//			for(int i = 0; i<2; ++i) {
//				celula = new PdfPCell(new Phrase("______________________________"));
//				celula.setBorder(PdfPCell.NO_BORDER);
//				celula.setHorizontalAlignment(Element.ALIGN_CENTER);
//				tabela.addCell(celula);
//			}
//			
//			p = new Phrase();
//			p.setFont(f2);
//			p.add(""+t.getVendedor().getNome());
//			celula = new PdfPCell(p);
//			celula.setBorder(PdfPCell.NO_BORDER);
//			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
//			tabela.addCell(celula);
//			
//			p = new Phrase();
//			p.setFont(f2);
//			p.add(""+t.getResponsavel().getNome());
//			celula = new PdfPCell(p);
//			celula.setBorder(PdfPCell.NO_BORDER);
//			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
//			tabela.addCell(celula);
//			
//			f2.setSize(10);
//			
//			p = new Phrase();
//			p.setFont(f);
//			p.add("Resp. pela venda");
//			celula = new PdfPCell(p);
//			celula.setBorder(PdfPCell.NO_BORDER);
//			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
//			tabela.addCell(celula);
//			
//			p = new Phrase();
//			p.setFont(f);
//			p.add("Resp. pela conferência");
//			celula = new PdfPCell(p);
//			celula.setBorder(PdfPCell.NO_BORDER);
//			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
//			tabela.addCell(celula);
//			
//			f.setStyle(Font.ITALIC);
//			d.add(tabela);
//		} catch (DocumentException | IOException e) {
//			e.printStackTrace();
//		} finally {
//			fecharArquivo();
//		}
//	}
//	
//	public void comprovanteCadastro(Aluno a) {
//		FormatarCampo fc = new FormatarCampo();
//		String datahora = fc.datahoraToString(hoje);
//		
//		try {
//			iniciarArquivo("ComprovanteCadastro"+a.getId()+".pdf");
//			for(int i = 0; i < 2; ++i) {
////				deve fazer duas vias: uma fica com o aluno e outra com a CTB
//				d.add(logoPequeno);
//				
//				paragrafo = new Paragraph("MEIA-PASSAGEM ESCOLAR - VALE ESTUDANTE\nCOMPROVANTE DE CADASTRO - "
//						+ hoje.getYear() + "\n\n");
//				paragrafo.setAlignment(1);
//				d.add(paragrafo);
//	
//				paragrafo = new Paragraph();
//				paragrafo.setFont(f);
//				paragrafo.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
//				paragrafo.add("Id: " + a.getStringId() + "         RG: " + a.getRg() + "         Idade: " + a.getIdade()
//						+ " anos         Limite Mensal: 50 vales\n");
//				d.add(paragrafo);
//	
//				paragrafo = new Paragraph();
//				paragrafo.setFont(f);
//				paragrafo.add("Nome: " + a.getNome() + " " + "                                Bairro: " + a.getBairro()
//						+ "\n" + "Escola: " + a.getEscola().getNome() + "  na localidade " + a.getEscola().getBairro());
//				d.add(paragrafo);
//	
//				paragrafo = new Paragraph();
//	
//				paragrafo.setAlignment(Element.ALIGN_CENTER);
//				paragrafo.add("TERMO DE UTILIZAÇÃO");
//				d.add(paragrafo);
//	
//				paragrafo = new Paragraph();
//				paragrafo.setFont(f);
//				paragrafo.setAlignment(Element.ALIGN_JUSTIFIED);
//				paragrafo.add("- Estudantes que não terão direito ao benefício da Meia Passagem Escolar - Vale Estudante:\n"
//						+ "I. Estudantes de pós-graduação, pré-vestibulares e cursos não regulamentados pelo MEC;\n"
//						+ "II. Estudantes que já gozem da gratuidade do Sistema de Transporte Ferroviário;\n"
//						+ "II. Estudantes menores que 06 (seis) anos;\n"
//						+ "Situações que implicam na suspensão do benefício:\n"
//						+ "I. Uso por terceiros, inclusive os seus acompanhantes; II. Uso por estudantes não cadastrados; III. Comercialização.\n"
//						+ "Os beneficiários do sistema de Meia Passagem Escolar - Vale Estudante deverão apresentar o Cartão de identificação, fornecido no ato do cadastramento, no momento em que for utilizar o seu benefício.");
//				d.add(paragrafo);
//	
//				paragrafo = new Paragraph();
//				paragrafo.setFont(f);
//				paragrafo.setAlignment(Element.ALIGN_CENTER);
//				paragrafo.add(
//						"- PERDA DO CARTÃO - No caso de perda ou roubo do cartão de identificação, solicitamos o Registro em Delegacia e posterior apresentação do Registro de Ocorrência");
//				d.add(paragrafo);
//	
//				paragrafo = new Paragraph();
//				paragrafo.setFont(f);
//				paragrafo.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
//				paragrafo.add(
//						"- Aceito os termos de utilização da Meia Passagem Escolar - Vale Estudante, inclusive os itens que implicam na suspensão do benefício e atesto o recebimento do Cartão de Meia Passagem Escolar.\n"
//								+ "Data: " + datahora + ""
//								+ "     Assinatura:_________________________________________________________\n");
//				d.add(paragrafo);
//	
//				paragrafo = new Paragraph();
//				paragrafo.setFont(f);
//				paragrafo.setAlignment(Element.ALIGN_CENTER);
//				
//				if (i == 0)
//					paragrafo.add("          ______________________________________________________________\n"
//							+ "                        Responsável pelo cadastro\n"
//							+ "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ \n");
//				else 
//						paragrafo.add("          ______________________________________________________________\n"
//								+ "                        Responsável pelo cadastro\n");
//				
//				d.add(paragrafo);
//			}
//		} catch (DocumentException | IOException e) {
//			e.printStackTrace();
//		} finally {
//			fecharArquivo();
//		}
//	}

	private void fecharArquivo() {
		d.close();

		/* descomente esse trecho para o arquivo abrir no SERVIDOR quando for gerado */
		try {
			File arquivo = new File(nomeArquivo);
			Desktop.getDesktop().open(arquivo);
		} catch (IOException e) {
			e.printStackTrace();
		}

	};

	private void iniciarArquivo(String string) throws DocumentException, MalformedURLException, IOException {
		nomeArquivo = pastaRaiz + subPasta  + string;

		new File(pastaRaiz + subPasta).mkdirs();
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream(nomeArquivo);
		} catch (FileNotFoundException e) {
			Runtime.getRuntime().exec("taskkill /F /IM AcroRd32.exe");
//			ProcessBuilder pb = new ProcessBuilder("tskill","AcroRd32.exe");
//			pb = pb.redirectErrorStream(true);
//			Process proc = pb.start();
			
			fos = new FileOutputStream(nomeArquivo);
		}
		
		PdfWriter.getInstance(d, fos);
		d.open();

		logoCtbPequeno = Image.getInstance(pastaRaiz + "layout\\images\\logo-ctb pequeno.png");
		logoPequeno = Image.getInstance(pastaRaiz + "layout\\images\\logo pequeno.png");
//		float teste = 300;
//		float documentWidth = d.getPageSize().getWidth() - d.leftMargin() - d.rightMargin() - teste;
//		float documentHeight = d.getPageSize().getHeight() - d.topMargin() - d.bottomMargin() - teste;
//		logoPequeno.scaleToFit(documentWidth, documentHeight);
//		logoCtbPequeno.setAlignment(Element.PARAGRAPH);
//		logoPequeno.scaleToFit(logoPequeno.getAbsoluteX(),logoPequeno.getAbsoluteY());
//		logoPequeno.setAlignment(Element.ALIGN_RIGHT);
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