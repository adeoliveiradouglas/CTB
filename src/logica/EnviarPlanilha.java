package logica;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class EnviarPlanilha implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
	/*	String origem = (String) pedido.getSession().getAttribute("origem");
		int n = Integer.parseInt((String) pedido.getSession().getAttribute("n"));
		*/
		/*Identifica se o formulario é do tipo multipart/form-data*/
        if (ServletFileUpload.isMultipartContent(pedido)) {
            try {
                /*Faz o parse do pedido*/
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(pedido);
 
                /*Escreve a o arquivo na pasta img*/
                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                    	String arquivo = "/" + item.getName();
                        item.write(new File(arquivo));
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
 
       return "/adds/ajuda.jsp";
	}

}
