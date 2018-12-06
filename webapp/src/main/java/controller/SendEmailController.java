package controller;

import java.util.List;
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

import dbConnector.ConnectToEmailDB;
import dbConnector.ConnectToUserDB;
import entity.Email;
import entity.User;

@ManagedBean
@SessionScoped
public class SendEmailController {

	private MainController mainController;

	private String emailName;

	public SendEmailController(MainController mainController) {
		this.mainController = mainController;
		this.emailName = "";
	}
	
	
	
	public User findUserByEmail() {
		List<Email> emailList = ConnectToEmailDB.queryEmail("from Email where name = '"+ emailName +"'");
		Email email = new Email();
		if(!emailList.isEmpty()) {
			email = emailList.get(0);
		}
		User user = email.getUser();
		return user;
	}

	public String sendEmail() {
		User user = findUserByEmail();
		mainController.getUserController().setUser(user);
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
			message.setSubject("Passwort ändern");
			message.setText("geschafft");
			message.setText("http://localhost:8080/webapp/changePassword.xhtml");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
		return "successSendEmail";
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
