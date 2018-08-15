package utilidades;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class Email {
	private SimpleEmail email;
	private String  assunto = null,
			mensagem = null,
			emailTo = null,
			nome = "Gestão de Contratos",
			smtp = "smtp.office365.com",
			senha = "$TEci.2018$",
			emailFrom = "contratos.ctb@ctb.ba.gov.br";
//			smtp = "smtp.gmail.com",
//			senha = "raspiberrypi",
//			emailFrom = "adeoliveiradouglas@gmail.com";
	private int smtpPorta = 587;

	public Email(){}
	
	public void enviarCodigo(String emailTo, int codigo){
		//envia a mensagem com o codigo para o email recebido no parametro
		this.assunto = "Recuperação de senha Gestão de Contratos - CTB";
		this.emailTo = emailTo;
		this.mensagem = "O token de recuperação é: " + codigo + ".\nSe não solicitou, desconsidere essa mensagem.";
		this.enviar();
	}
	
	public void enviarConfirmacaoCadastro(String emailTo, String nome){
		this.assunto = "Confirmação de cadastro";
		this.emailTo = emailTo;
		this.mensagem = nome + ", obrigado por se cadastrar no Sistema de Gestão de Contratos " +
			"da CTB. Agora basta aguardar o administrador liberar o seu acesso " +
			"para começar a usar. Te enviaremos outro email quando seu acesso for aprovado.";
		this.enviar();
	}
	
	
	public void aviso90dias(String emailTo){
//		gestor do contrato, fiscal e o gestor geral
		this.assunto = "Faltam 90 dias para vencimento do contrato";
		this.mensagem = "faltam 90 dias para vencimento do contrato";
		this.emailTo = emailTo;
		this.enviar();
	}

	public void aviso60dias(String emailTo){
//		para os acima e mais o diretor
		this.assunto = "Faltam 60 dias para vencimento do contrato";
		this.mensagem = "faltam 60 dias para vencimento do contrato";
		this.emailTo = emailTo;
		this.enviar();
	}
	
	public void aviso45dias(String emailTo){
//		todos e mais o presidente	
		this.assunto = "Faltam 45 dias para vencimento do contrato";
		this.mensagem = "faltam 45 dias para vencimento do contrato";
		this.emailTo = emailTo;
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
//		    email.send();
		} catch (EmailException e) {
		    e.printStackTrace();
		}
	}
}
