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

public class ConnectToImageDB {

	private static SessionFactory instance;

	private static Session getInstance() {
		if (instance == null) {
			instance = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
					.addAnnotatedClass(Pdf.class).addAnnotatedClass(Link.class).addAnnotatedClass(Image.class)
					.addAnnotatedClass(Like.class).buildSessionFactory();
		}
		return instance.getCurrentSession();
	}

	public static void saveImageInDB(Image image) {
		Session session = getInstance();
		try {

			session.beginTransaction();

			session.save(image);

			session.getTransaction().commit();

		} finally {
			getInstance().close();
		}

	}

	public static Image getImageFromDB(int idImage) {
		Session session = getInstance();
		Image image;
		try {
			session.beginTransaction();

			image = session.get(Image.class, idImage);

			session.getTransaction().commit();
		} finally {
			getInstance().close();
		}
		return image;
	}

	@SuppressWarnings("unchecked")
	public static List<Image> queryImage(String query) {

		Session session = getInstance();
		List<Image> theImages;

		try {

			// start a transaction
			session.beginTransaction();

			// query students
			theImages = session.createQuery(query).list();

			// commit transaction
			session.getTransaction().commit();

		} finally {
			getInstance().close();
		}
		return theImages;
	}

	public static void displayImages(List<Image> theImages) {
		for (Image tempImage : theImages) {
			System.out.println(tempImage);
		}
	}

}
