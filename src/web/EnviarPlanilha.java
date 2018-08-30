package web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import entity.Contrato;
import entity.Processo;
import utilidades.Planilha;

public class EnviarPlanilha implements Logica {

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		final String formatoArquivo = ".xls";
		File planilha = null;

		String origem = "" + pedido.getSession().getAttribute("origem"), teste = null;
		int n = Integer.parseInt("" + pedido.getSession().getAttribute("n"));

		@SuppressWarnings("unchecked")
		ArrayList<Contrato> contratos = ((ArrayList<Contrato>) pedido.getSession().getAttribute(origem));
		int idContrato = contratos.get(n).getId();

		/* Identifica se o formulario é do tipo multipart/form-data */
		if (ServletFileUpload.isMultipartContent(pedido)) {
			try {
				/* Faz o parse do pedido */
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(pedido);

				/* Escreve a o arquivo na pasta img */
				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						String formatoArquivoRecebido = item.getName().substring(item.getName().length() - 4,
								item.getName().length());

						if (formatoArquivoRecebido.contains(formatoArquivo)) {
							// para processar somente arquivos excel
							teste = item.getName();
							planilha = new File(new File(System.getProperty("user.home")), teste);
							item.write(planilha);
						} else {
							return "sistema?logica=ErroFormato";
						}
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			ArrayList<Processo> previaProcessos = new Planilha().carregar(new File(System.getProperty("user.home"), teste),
					idContrato);
			pedido.getSession().setAttribute("previaProcessos", previaProcessos);
			
			planilha.delete();
		}

		return "/Gestor/previaContrato.jsp";
	}

}
