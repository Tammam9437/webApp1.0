package dbConnector;

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
import exception.UserIsNullException;

public class ConnectToImageDB {

	private static SessionFactory instance;

	private static Session getInstance() {
		if (instance == null) {
			instance = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
					.addAnnotatedClass(Pdf.class).addAnnotatedClass(Link.class).addAnnotatedClass(Image.class)
					.addAnnotatedClass(Email.class).addAnnotatedClass(Li.class).addAnnotatedClass(Category.class)
					.addAnnotatedClass(Comment.class).buildSessionFactory();
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

	public static void deleteImageFromDB(int imageId) {
		Session session = getInstance();
		try {
			session.beginTransaction();

			session.createQuery("delete from Image where id= " + imageId).executeUpdate();

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
	
	public static List<Image> getUserImagesFromDB(User user) {
		if (user != null) {
			List<Image> list = ConnectToImageDB.queryImage("From Image WHERE iduser ='" + user.getId() + "'");
			return list;
		} else {
			throw new UserIsNullException("melden sie sich bitte erneut an");
		}
	}


	public static void displayImages(List<Image> theImages) {
		for (Image tempImage : theImages) {
			System.out.println(tempImage);
		}
	}

}
