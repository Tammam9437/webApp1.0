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
import entity.YoutubeLink;

public class ConnectToLinkDB {

	private static SessionFactory instance;

	private static Session getInstance() {
		if (instance == null) {
			instance = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
					.addAnnotatedClass(Pdf.class).addAnnotatedClass(Link.class).addAnnotatedClass(Image.class)
					.addAnnotatedClass(Li.class).addAnnotatedClass(YoutubeLink.class).addAnnotatedClass(Email.class)
					.addAnnotatedClass(Category.class).buildSessionFactory();
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
	
	public static Link getLinkFromDB(int linkId) {
		Session session = getInstance();
		Link link;
		try {
			session.beginTransaction();

			link = session.get(Link.class, linkId);

			session.getTransaction().commit();
		} finally {
			getInstance().close();
		}
		return link;
	}
	
	public static void deleteLinkFromDB(int linkId) {
		Session session = getInstance();
		try {
			session.beginTransaction();
			
			session.createQuery("delete from Link where id= " + linkId).executeUpdate();
			
			session.getTransaction().commit();
			
		}finally {
			getInstance().close();
		}
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

	public static void displayLinks(List<Link> theLinks) {
		for (Link tempLink : theLinks) {
			System.out.println(tempLink);
		}
	}
}
