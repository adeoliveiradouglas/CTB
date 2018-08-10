package logica;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import entity.Contrato;

public class EnviarPlanilha implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		final String formatoArquivo = ".xlsx";
		
		String origem = (String) pedido.getSession().getAttribute("origem");
		int n = Integer.parseInt((String) pedido.getSession().getAttribute("n"));
		@SuppressWarnings("unchecked")
		int idContrato = ((ArrayList<Contrato>) pedido.getSession().getAttribute(origem)).get(n).getId();
		
		/*Identifica se o formulario é do tipo multipart/form-data*/
        if (ServletFileUpload.isMultipartContent(pedido)) {
            try {
                /*Faz o parse do pedido*/
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(pedido);
 
                /*Escreve a o arquivo na pasta img*/
                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                    	String formatoArquivoRecebido = item.getName().substring(item.getName().length() - 5, item.getName().length());
                    	
                    	if(formatoArquivoRecebido.contains(formatoArquivo)){
                    		//para processar somente arquivos excel
                    		item.write(new File("/home/server/" + item.getName()));
                    	}                    	
                    }
                }
 
                pedido.setAttribute("message", "Arquivo carregado com sucesso");
            } catch (Exception ex) {
            	ex.printStackTrace();
                pedido.setAttribute("message", "Upload de arquivo falhou devido a "+ ex);
            }
 
        } else {
            pedido.setAttribute("message","Desculpe este Servlet lida apenas com pedido de upload de arquivos");
        }
 
       return "sistema?logica=VerO";
	}

}
