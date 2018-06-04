/*
 * Servlet respons�vel por recuperar senha
 * Gera um token num�rico de 4 d�gitos e envia para o email do usuario 
 */

package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.SimpleEmail;

@WebServlet("/recuperarsenhaservlet") //nome da servlet
public class RecuperarSenhaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta) throws IOException {
		//Gera o token
		int token = new Random().nextInt(8999) + 1000;

		PrintWriter out = resposta.getWriter();
		out.println("<html>");
        out.println("<body>");
        out.println("<p>" + token + "</p>");
        out.println("</body>");
        out.println("</html>");
        
        /*� melhor chamar essa fun��o no final do m�todo pois desse jeito n�o atrasa a exibi��o da p�gina*/
        enviarCodigoEmail(pedido.getParameter("email"), token);
	}
	
	private void enviarCodigoEmail(String emailTo, int codigo){
		//envia a mensagem com o codigo para o email recebido no parametro
		
		SimpleEmail mail = new SimpleEmail();
		String  assunto = "Recupera��o de senha Gest�o de Contratos - CTB",
				mensagem = "O token de recupera��o �: " + codigo + ".\nSe n�o solicitou, desconsidere essa mensagem.",
				smtp = "smtp.office365.com",
				emailFrom = "contratos.ctb@ctb.ba.gov.br",
				senha = "", //falta colocar senha do email
				nome = "Gest�o de Contratos";
		int smtpPorta = 587;

		try {
			//monta a mensagem
			mail.setFrom(emailFrom, nome);
			mail.setSubject(assunto);
			mail.setMsg(mensagem);
			mail.setSSLOnConnect(true);
			mail.setAuthentication(emailFrom, senha);
			mail.setHostName(smtp);
			mail.setSmtpPort(smtpPorta);
			mail.addTo(emailTo);
			//envia a mensagem
			mail.send();
		} catch (Exception e) {}
	}
}