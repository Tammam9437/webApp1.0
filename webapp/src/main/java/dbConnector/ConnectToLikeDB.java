package dbConnector;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entity.Category;
import entity.Comment;
import entity.Email;
import entity.Image;
import entity.Li;
import entity.Link;
import entity.Pdf;
import entity.User;
import entity.YoutubeLink;

public class ConnectToLikeDB {

	private static SessionFactory instance;

	private static Session getInstance() {
		if (instance == null) {
			instance = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
					.addAnnotatedClass(Pdf.class).addAnnotatedClass(Link.class).addAnnotatedClass(Image.class)
					.addAnnotatedClass(Email.class).addAnnotatedClass(Email.class).addAnnotatedClass(Li.class)
					.addAnnotatedClass(Comment.class).addAnnotatedClass(Category.class).buildSessionFactory();
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

	public static void deleteLikeFromDB(int likeId) {
		Session session = getInstance();
		try {
			session.beginTransaction();

			session.createQuery("delete from Li where id= " + likeId).executeUpdate();

			session.getTransaction().commit();

		} finally {
			getInstance().close();
		}
	}

	public static void queryDeleteLikeFromDB(String query) {
		Session session = getInstance();
		try {
			session.beginTransaction();

			session.createQuery(query).executeUpdate();

			session.getTransaction().commit();

		} finally {
			getInstance().close();
		}
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

	public static Li getLikeFromDB(int idlike) {
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

	public static List<Li> getUserLikes(User user) {
		List<Li> userLikes = queryLike("From Li WHERE iduser ='" + user.getId() + "'");
		return userLikes;
	}

	public static List<Li> getLinkLikes(Link link) {
		List<Li> userLikes = queryLike("From Li WHERE idlink ='" + link.getId() + "'");
		return userLikes;
	}

	public static List<Li> getUserFavoritLinks(User user) {
		List<Li> userLikes = queryLike("From Li WHERE idLink IS NOT NULL AND iduser ='" + user.getId() + "'");
		List<Li> userFavoritLinks = new ArrayList<>();
		for (Li like : userLikes) {
			if (!(like.getLink() instanceof YoutubeLink) && like.getLink() != null) {
				userFavoritLinks.add(like);
			}
		}
		return userFavoritLinks;
	}

	public static List<Li> getUserLinkLikes(User user, Link link) {
		List<Li> userLinkLikes = queryLike(
				"From Li WHERE iduser ='" + user.getId() + "'" + " AND idlink ='" + link.getId() + "'");
		return userLinkLikes;
	}

	public static List<Li> getUserImageLikes(User user, Image image) {
		List<Li> userLinkLikes = queryLike(
				"From Li WHERE iduser ='" + user.getId() + "'" + " AND idImage ='" + image.getIdImage() + "'");
		return userLinkLikes;
	}

	public static List<Li> getUserPdfLikes(User user, Pdf pdf) {
		List<Li> userPdfLikes = queryLike(
				"From Li WHERE iduser ='" + user.getId() + "'" + " AND idPdf ='" + pdf.getIdPdf() + "'");
		return userPdfLikes;
	}

	public static void displayLikes(List<Li> theLikes) {
		for (Li tempLike : theLikes) {
			System.out.println(tempLike);
		}
	}

}
