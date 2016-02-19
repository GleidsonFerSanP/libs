import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;


public class EmailComAnexoInputstream{
	static Logger logger = Logger.getLogger(EmailComAnexoInputstream.class);
	
	private MimeMessage msg;
	private Multipart mp;

	public EmailComAnexoInputstream() {
		
		Properties props = new Properties();
		/*	props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.socketFactory.port", ConstantesProp.SMTP_PORTA);
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			*/
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.host", ConstantesProp.SMTP_SERVER);
			props.put("mail.smtp.port", ConstantesProp.SMTP_PORTA);

			if( ehSmtpGoogle(props) ) {
				props.put("mail.smtp.port", 465);
				props.put("mail.smtp.ssl.port", 465);
				props.put("mail.smtp.tls", true);

				props.getProperty("mail.smtp.starttls.enable","true");
				props.getProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
				props.getProperty("mail.smtp.socketFactory.fallback","false");

			}

			Session session = Session.getInstance(props,new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(ConstantesProp.EMAIL_USUARIO, ConstantesProp.EMAIL_SENHA);
				}
			});

		msg = new MimeMessage(session);
		mp = new MimeMultipart();

	}

	public void addToEmailsConcatenados(String emailsTo, String nomeTo) throws EmailException, MessagingException {
		List<String> emailsLista = Uteis.formarListaComString(emailsTo, ";");

		for (String email : emailsLista) {
			msg.setRecipients(Message.RecipientType.TO, email);
		}
	}

	public void sendMail() {
		try {
			
			validar();

			msg.setContent(mp);
			
			Transport.send(msg);

		} catch (MessagingException e) {
			logger.error("ERRO NO ENVIO DO EMAIL com anexo", e);
		}
	}

	private void validar() throws MessagingException {
		Assert.notNull(msg.getFrom(),"O email do remetente não pode ser vazio");
		Assert.notNull(msg.getRecipients(Message.RecipientType.TO),"Não foi inserido nenhum email destinatário");
		Assert.notNull(msg.getSubject(),"Adicione o assunto do email");
	}

	private boolean ehSmtpGoogle(Properties props) {
		return props.getProperty("smtp.gmail.com") != null && props.getProperty("smtp.gmail.com").equals( ConstantesProp.SMTP_SERVER );
	}

	public void setHtmlMsg(String texto) throws MessagingException {
		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(texto, "text/html; charset=utf-8");
		mp.addBodyPart(mimeBodyPart);
	}

	public void setFrom(String emailUsuario, String nomeEmpresa) throws UnsupportedEncodingException, MessagingException{
		msg.setFrom(new InternetAddress(emailUsuario,nomeEmpresa,"UTF-8"));
	}

	public void addReplyTo(String email, String nome) throws MessagingException {
		msg.setRecipients(Message.RecipientType.TO, email);
	}

	public void setSubject(String assuntoEmail) throws MessagingException {
		msg.setSubject(assuntoEmail);
	}

	public void setAttachment(MultipartFile file) throws MessagingException, IOException{
		InputStreamDataSource attach = new InputStreamDataSource(file.getOriginalFilename(),"application/octet-stream", file.getInputStream());
		MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setDataHandler(new DataHandler(attach));
		mimeBodyPart.setFileName(attach.getName());
		mp.addBodyPart(mimeBodyPart);
	}

	public void setDateSend(Date date) throws MessagingException {
		msg.setSentDate(new Date());
	}
}
