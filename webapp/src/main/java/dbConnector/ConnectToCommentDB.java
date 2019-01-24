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

public class ConnectToCommentDB {

	private static SessionFactory instance;

	private static Session getInstance() {
		if (instance == null) {
			instance = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
					.addAnnotatedClass(Pdf.class).addAnnotatedClass(Link.class).addAnnotatedClass(Image.class)
					.addAnnotatedClass(Email.class).addAnnotatedClass(Email.class).addAnnotatedClass(Li.class)
					.addAnnotatedClass(Category.class).addAnnotatedClass(Comment.class).buildSessionFactory();
		}
		return instance.getCurrentSession();
	}

	public static void saveCommentInDB(Comment comment) {
		Session session = getInstance();
		try {

			session.beginTransaction();

			session.save(comment);

			session.getTransaction().commit();

		} finally {
			getInstance().close();
		}

	}

	public static void deleteCommentFromDB(int commentId) {
		Session session = getInstance();
		try {
			session.beginTransaction();

			session.createQuery("delete from Comment where id= " + commentId).executeUpdate();

			session.getTransaction().commit();

		} finally {
			getInstance().close();
		}
	}
	
	public static void queryDeleteCommentFromDB(String query) {
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
	public static List<Comment> queryComment(String query) {
	
		Session session = getInstance();
		List<Comment> theComments;
	
		try {
	
			// start a transaction
			session.beginTransaction();
	
			// query students
			theComments = session.createQuery(query).list();
	
			// commit transaction
			session.getTransaction().commit();
	
		} finally {
			getInstance().close();
		}
		return theComments;
	}

	public static Comment getCommentFromDB(int idComment) {
		Session session = getInstance();
		Comment comment;
		try {
			session.beginTransaction();

			comment = session.get(Comment.class, idComment);

			session.getTransaction().commit();
		} finally {
			getInstance().close();
		}
		return comment;
	}

	public static List<Comment> getUserComments(User user) {
		List<Comment> userComments = queryComment("From Comment WHERE iduser ='" + user.getId() + "'");
		return userComments;
	}

	public static List<Comment> getImageComments(Image image) {
		List<Comment> userComments = queryComment("From Comment WHERE idImage ='" + image.getIdImage() + "'");
		return userComments;
	}
		

}
