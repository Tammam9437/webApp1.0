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

public class ConnectToLikeDB {

	private static SessionFactory instance;

	private static Session getInstance() {
		if (instance == null) {
			instance = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
					.addAnnotatedClass(Pdf.class).addAnnotatedClass(Link.class).addAnnotatedClass(Image.class)
					.addAnnotatedClass(Li.class).buildSessionFactory();
		}
		return instance.getCurrentSession();
	}

	public static void saveLikeInDB(Li like) {
		Session session = getInstance();
		try {

			session.beginTransaction();

			session.save(like);

			session.getTransaction().commit();

		} finally {
			getInstance().close();
		}

	}

	public static Li getPdfFromDB(int idlike) {
		Session session = getInstance();
		Li like;
		try {
			session.beginTransaction();

			like = session.get(Li.class, idlike);

			session.getTransaction().commit();
		} finally {
			getInstance().close();
		}
		return like;
	}
	
	public static List<Li> getUserLikes(User user){
		List<Li> userLikes = queryLike("From Li WHERE iduser ='" + user.getId() + "'");
		return userLikes;
	}

	public static List<Li> getLinkLikes(Link link){
		List<Li> userLikes = queryLike("From Li WHERE iduser ='" + link.getId() + "'");
		return userLikes;
	}
	public static List<Li> getUserLinkLikes(User user,Link link){
		List<Li> userLinkLikes = queryLike("From Li WHERE iduser ='" + user.getId() + "'"+" AND idlink ='"+ link.getId() + "'");
		return userLinkLikes;
	}

	@SuppressWarnings("unchecked")
	public static List<Li> queryLike(String query) {

		Session session = getInstance();
		List<Li> theLikes;

		try {

			// start a transaction
			session.beginTransaction();

			// query students
			theLikes = session.createQuery(query).list();

			// commit transaction
			session.getTransaction().commit();

		} finally {
			getInstance().close();
		}
		return theLikes;
	}

	public static void displayLikes(List<Li> theLikes) {
		for (Li tempLike : theLikes) {
			System.out.println(tempLike);
		}
	}

}
