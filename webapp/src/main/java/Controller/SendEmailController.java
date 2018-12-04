package Controller;

import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@ManagedBean
@SessionScoped
public class SendEmailController {

	private MainController mainController;

	private String emailName;

	public SendEmailController(MainController mainController) {
		this.mainController = mainController;
		this.emailName = "";
	}

	public void sendEmail() {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("Wepapp332111@gmail.com", "0932167205");
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("Wepapp332111@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailName));
			message.setSubject("Passwort �ndern");
			message.setText("geschafft");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public MainController getMainController() {
		return mainController;
	}

	public String getEmailName() {
		return emailName;
	}

	public void setEmailName(String emailName) {
		this.emailName = emailName;
	}

}
