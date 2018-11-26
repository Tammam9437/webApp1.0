package DBConnector;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Entity.Image;
import Entity.Like;
import Entity.Link;
import Entity.Pdf;
import Entity.User;

public class ConnectToLikeDB {

	private static SessionFactory instance;

	private static Session getInstance() {
		if (instance == null) {
			instance = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
					.addAnnotatedClass(Pdf.class).addAnnotatedClass(Link.class).addAnnotatedClass(Image.class)
					.addAnnotatedClass(Like.class).buildSessionFactory();
		}
		return instance.getCurrentSession();
	}

	public static void saveLikeInDB(Like like) {
		Session session = getInstance();
		try {

			session.beginTransaction();

			session.save(like);

			session.getTransaction().commit();

		} finally {
			getInstance().close();
		}

	}

	public static Like getPdfFromDB(int idlike) {
		Session session = getInstance();
		Like like;
		try {
			session.beginTransaction();

			like = session.get(Like.class, idlike);

			session.getTransaction().commit();
		} finally {
			getInstance().close();
		}
		return like;
	}
	
	public static List<Like> getUserLikes(User user){
		List<Like> userLikes = queryLike("From Likes WHERE iduser ='" + user.getId() + "'");
		return userLikes;
	}

	public static List<Like> getLinkLikes(Link link){
		List<Like> userLikes = queryLike("From Likes WHERE iduser ='" + link.getId() + "'");
		return userLikes;
	}

	@SuppressWarnings("unchecked")
	public static List<Like> queryLike(String query) {

		Session session = getInstance();
		List<Like> theLikes;

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

	public static void displayLikes(List<Like> theLikes) {
		for (Like tempLike : theLikes) {
			System.out.println(tempLike);
		}
	}

}
