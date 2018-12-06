package dbConnector;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entity.Email;
import entity.Image;
import entity.Li;
import entity.Link;
import entity.Pdf;
import entity.User;
import entity.YoutubeLink;

public class ConnectToEmailDB {

	private static SessionFactory instance;

	private static Session getInstance() {
		if (instance == null) {
			instance = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
					.addAnnotatedClass(Pdf.class).addAnnotatedClass(Link.class).addAnnotatedClass(Image.class)
					.addAnnotatedClass(Li.class).addAnnotatedClass(YoutubeLink.class).addAnnotatedClass(Email.class)
					.buildSessionFactory();
		}
		return instance.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public static List<Email> queryEmail(String query) {

		Session session = getInstance();
		List<Email> theEmails;

		try {

			// start a transaction
			session.beginTransaction();

			// query students
			theEmails = session.createQuery(query).list();

			// commit transaction
			session.getTransaction().commit();

		} finally {
			getInstance().close();
		}
		return theEmails;
	}

	public static void saveEmailInDB(Email email) {
		Session session = getInstance();
		try {

			session.beginTransaction();

			session.save(email);

			session.getTransaction().commit();

		} finally {
			getInstance().close();
		}

	}

	public static void displayEmails(List<Email> theEmails) {
		for (Email tempEmail : theEmails) {
			System.out.println(tempEmail);
		}
	}
}
