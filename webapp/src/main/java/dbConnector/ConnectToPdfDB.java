package dbConnector;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entity.Category;
import entity.Email;
import entity.Image;
import entity.Li;
import entity.Link;
import entity.Pdf;
import entity.User;

public class ConnectToPdfDB {

	private static SessionFactory instance;

	private static Session getInstance() {
		if (instance == null) {
			instance = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
					.addAnnotatedClass(Pdf.class).addAnnotatedClass(Link.class).addAnnotatedClass(Image.class)
					.addAnnotatedClass(Email.class).addAnnotatedClass(Li.class).addAnnotatedClass(Category.class)
					.buildSessionFactory();
		}
		return instance.getCurrentSession();
	}

	public static void savePdfInDB(Pdf pdf) {
		Session session = getInstance();
		try {

			session.beginTransaction();

			session.save(pdf);

			session.getTransaction().commit();

		} finally {
			getInstance().close();
		}

	}

	public static Pdf getPdfFromDB(int idPdf) {
		Session session = getInstance();
		Pdf pdf;
		try {
			session.beginTransaction();

			pdf = session.get(Pdf.class, idPdf);

			session.getTransaction().commit();
		} finally {
			getInstance().close();
		}
		return pdf;
	}

	@SuppressWarnings("unchecked")
	public static List<Pdf> queryPdf(String query) {

		Session session = getInstance();
		List<Pdf> thePdfs;

		try {

			// start a transaction
			session.beginTransaction();

			// query students
			thePdfs = session.createQuery(query).list();

			// commit transaction
			session.getTransaction().commit();

		} finally {
			getInstance().close();
		}
		return thePdfs;
	}

	public static void displayPdfs(List<Pdf> thePdfs) {
		for (Pdf tempPdf : thePdfs) {
			System.out.println(tempPdf);
		}
	}

}
