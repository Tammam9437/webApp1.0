package DBConnector;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Entity.Image;
import Entity.Li;
import Entity.Link;
import Entity.Pdf;
import Entity.User;

public class ConnectToLinkDB {

	private static SessionFactory instance;

	private static Session getInstance() {
		if (instance == null) {
			instance = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
					.addAnnotatedClass(Pdf.class).addAnnotatedClass(Link.class).addAnnotatedClass(Image.class).addAnnotatedClass(Li.class)
					.buildSessionFactory();
		}
		return instance.getCurrentSession();
	}

	// **************** Link ****************
	@SuppressWarnings("unchecked")
	public static List<Link> queryLink(String query) {

		Session session = getInstance();
		List<Link> theLinks;

		try {

			// start a transaction
			session.beginTransaction();

			// query students
			theLinks = session.createQuery(query).list();

			// commit transaction
			session.getTransaction().commit();

		} finally {
			getInstance().close();
		}
		return theLinks;
	}

	public static void saveLinkInDB(Link link) {
		Session session = getInstance();
		try {

			session.beginTransaction();

			session.save(link);

			session.getTransaction().commit();

		} finally {
			getInstance().close();
		}

	}

//	public static void updateLink(int idlink) {
//		Session session = getInstance();
//		try {
//
//			session.beginTransaction();
//
//			Link link = session.get(Link.class, idlink);
//			System.out.println("der geholte link");
//			System.out.println(link.toString());
//
//			session.getTransaction().commit();
//
//		} finally {
//			getInstance().close();
//		}
//	}

	public static void displayLinks(List<Link> theLinks) {
		for (Link tempLink : theLinks) {
			System.out.println(tempLink);
		}
	}
}
