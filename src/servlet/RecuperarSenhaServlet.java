/*
 * Servlet responsável por recuperar senha
 * Gera um token e envia para o email do usuario 
 * */

package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.SimpleEmail;

@SuppressWarnings("serial")
@WebServlet("/recuperarsenhaservlet")
public class RecuperarSenhaServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta) throws IOException {
		int i = gerarCodigoEEnviarEmail();
		PrintWriter out = resposta.getWriter();
		
		out.println("<html>");
        out.println("<body>");
        out.println(i);
        out.println("</body>");
        out.println("</html>");
	}
	
	private int gerarCodigoEEnviarEmail(){
		SimpleEmail mail = new SimpleEmail();
		int codigo = new Random().nextInt(8999) + 1000;
		String  assunto = "Recuperação de senha Gestão de Contratos - CTB",
				mensagem = "O token de recuperação é: " + codigo + ".\nSe não solicitou, desconsidere essa mensagem.",
				smtp = "smtp.gmail.com",
				emailFrom = "adeoliveiradouglas@gmail.com",
				senha = "raspiberrypi",
				nome = "Gestão de Contratos",
				emailTo = "adeoliveiradouglas@gmail.com";
		int smtpPorta=465;

		try {
			mail.setFrom(emailFrom, nome);
			mail.setSubject(assunto);
			mail.setMsg(mensagem);
			mail.setSSLOnConnect(true);
			mail.setAuthentication(emailFrom, senha);
			mail.setHostName(smtp);
			mail.setSmtpPort(smtpPorta);
			mail.addTo(emailTo);
			mail.send();
		} catch (Exception e) {}
		return codigo;
	}
}