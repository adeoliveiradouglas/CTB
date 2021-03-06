package utilidades;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import entity.Contrato;

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
	
	
	public void aviso90dias(String emailTo, Contrato c){
//		gestor do contrato, fiscal e o gestor geral
		this.assunto = "Faltam 90 dias para vencimento do contrato";
		this.mensagem = 
			"Prezado(a) Gestor(a),\n" +
			"Considerando que o contrato nº: " + c.getNumero() + " firmado entre a Contratada e a Companhia de Transportes do "
			+ "Estado da Bahia faltam menos de 90 (noventa) dias para o fim de vigência do contrato.\n"+
			"Conforme o art. 142 e o parágrafo único da Lei 9.433/2005 qualquer prorrogação dos contratos de prestação a "
			+ "serem executado de forma contínua deverá ser solicitada ainda no prazo de vigência do contrato, a prorrogação "
			+ "dos contratos deverá ser realizada pelo servidor responsável pelo seu acompanhamento no prazo máximo de até 60 "
			+ "(sessenta) dias antes do termo final. diante disso solicitamos abertura do processo de prorrogação, devendo "
			+ "assim o processo ser encaminhando a QUAS para conhecimento.\n\n"
			+ "Este email foi encaminhado para o gestor do contrato, fiscal do contrato e QUAS";
		
		this.emailTo = emailTo;
		this.enviar();
	}

	public void aviso60dias(String emailTo, Contrato c){
//		para os acima e mais o diretor
		this.assunto = "Faltam 60 dias para vencimento do contrato";
		this.mensagem = 
			"Prezado(a) Gestor(a),\n" +
			"Considerando que o contrato nº: " + c.getNumero() + " firmado entre a Contratada e a Companhia de Transportes do "
			+ "Estado da Bahia faltam menos de 60 (sessenta) dias para o fim de vigência do contrato.\n"+
			"Conforme o art. 142 e o parágrafo único da Lei 9.433/2005 qualquer prorrogação dos contratos de prestação a "
			+ "serem executado de forma contínua deverá ser solicitada ainda no prazo de vigência do contrato, a prorrogação "
			+ "dos contratos deverá ser realizada pelo servidor responsável pelo seu acompanhamento no prazo máximo de até 60 "
			+ "(sessenta) dias antes do termo final. diante disso solicitamos abertura do processo de prorrogação, devendo "
			+ "assim o processo ser encaminhando a QUAS para conhecimento.\n\n"
			+ "Este email foi encaminhado para o gestor do contrato, fiscal do contrato, QUAS e diretor";
			
		this.emailTo = emailTo;
		this.enviar();
	}
	
	public void aviso45dias(String emailTo, Contrato c){
//		todos e mais o presidente	
		this.assunto = "Faltam 45 dias para vencimento do contrato";
		this.mensagem = 
			"Prezado(a) Gestor(a),\n" +
			"Considerando que o contrato nº: " + c.getNumero() + " firmado entre a Contratada e a Companhia de Transportes do "
			+ "Estado da Bahia faltam menos de 45 (quarenta e cinco) dias para o fim de vigência do contrato.\n"+
			"Conforme o art. 142 e o parágrafo único da Lei 9.433/2005 qualquer prorrogação dos contratos de prestação a "
			+ "serem executado de forma contínua deverá ser solicitada ainda no prazo de vigência do contrato, a prorrogação "
			+ "dos contratos deverá ser realizada pelo servidor responsável pelo seu acompanhamento no prazo máximo de até 60 "
			+ "(sessenta) dias antes do termo final. diante disso solicitamos abertura do processo de prorrogação, devendo "
			+ "assim o processo ser encaminhando a QUAS para conhecimento.\n\n"
			+ "Este email foi encaminhado para o gestor do contrato, fiscal do contrato, QUAS e diretor e presidente";
			
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
		    email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
}
