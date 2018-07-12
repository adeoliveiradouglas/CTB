package utilidades;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Email {
	private SimpleEmail email;
	private String  assunto = null,
			mensagem = null,
			emailTo = null,
			nome = "Gest�o de Contratos",
			smtp = "smtp.office365.com",
			senha = "$TEci.2018$",
			emailFrom = "contratos.ctb@ctb.ba.gov.br";
	private int smtpPorta = 587;

	public Email(){}
	
	public void enviarCodigo(String emailTo, int codigo){
		//envia a mensagem com o codigo para o email recebido no parametro
		this.assunto = "Recupera��o de senha Gest�o de Contratos - CTB";
		this.emailTo = emailTo;
		this.mensagem = "O token de recupera��o �: " + codigo + ".\nSe n�o solicitou, desconsidere essa mensagem.";
		this.enviar();
	}
	
	public void enviarConfirmacaoCadastro(String emailTo, String nome){
		this.assunto = "Confirma��o de cadastro";
		this.emailTo = emailTo;
		this.mensagem = nome + ", obrigado por se cadastrar no Sistema de Gest�o de Contratos " +
			"da CTB. Agora basta aguardar o administrador liberar o seu acesso " +
			"para come�ar a usar. Te enviaremos outro email quando seu acesso for aprovado.";
		this.enviar();
	}
	
	private void enviar(){
		email = new SimpleEmail();
		
		email.setHostName(smtp);
		email.setSmtpPort(smtpPorta);
		email.setAuthenticator(new DefaultAuthenticator(emailFrom, senha));
		email.setStartTLSEnabled(true);
		try {
		    email.setFrom(emailFrom, nome);
		    email.setSubject(assunto);
		    email.setDebug(true);
		    email.setMsg(mensagem);
		    email.addTo(emailTo);
		    email.send();
		} catch (EmailException e) {
		    e.printStackTrace();
		}
	}
}
