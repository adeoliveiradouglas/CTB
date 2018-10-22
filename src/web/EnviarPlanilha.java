package web;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import entity.Contrato;
import entity.Dados;
import utilidades.Planilha;

public class EnviarPlanilha implements Logica {

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		final String formatoArquivoXLSX = "xlsx",
					 formatoArquivoXLS = "xls";
		File planilha = null;

		String nomeDoArquivo = null;
		
		Contrato contrato = (Contrato) pedido.getSession().getAttribute("contratoVisualizar");

		List<Dados> previaProcessos = null;
		
		/* Identifica se o formulario é do tipo multipart/form-data */
		if (ServletFileUpload.isMultipartContent(pedido)) {
			try {
				/* Faz o parse do pedido */
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(pedido);

				/* Escreve a o arquivo na pasta img */
				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						String formatoArquivoRecebido = item.getName().substring(item.getName().lastIndexOf(".") + 1,
								item.getName().length());

						// para processar somente arquivos excel
						nomeDoArquivo = item.getName();
						planilha = new File(new File(System.getProperty("user.home")), nomeDoArquivo);
						item.write(planilha);
						
						if (formatoArquivoRecebido.equals(formatoArquivoXLSX)) {
							previaProcessos = new Planilha().
								carregarXLSX( 
									planilha,
									contrato
								);
						} else if (formatoArquivoRecebido.equals(formatoArquivoXLS)) {
							previaProcessos = new Planilha().
								carregarXLS( 
									planilha,
									contrato
								);
						} else {
							return "sistema?logica=ErroFormato";
						}
					}
				}
			} catch (FileNotFoundException e) {
				return "sistema?logica=ErroArquivoInexistente";
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			BigDecimal totalAditivos = new BigDecimal("0");
			for (Dados d: previaProcessos)
				totalAditivos = totalAditivos.add(d.getAditivo());
				
			contrato.setValorAditivos(totalAditivos);
			contrato.setDados(previaProcessos);
			
			planilha.delete();
		}

		return "/Gestor/previaContrato.jsp";
	}

}
