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

public class ConnectToUserDB {

	private static SessionFactory instance;

	private static Session getInstance() {
		if (instance == null) {
			instance = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
					.addAnnotatedClass(Pdf.class).addAnnotatedClass(Link.class).addAnnotatedClass(Image.class)
					.addAnnotatedClass(Email.class).addAnnotatedClass(Li.class).buildSessionFactory();
		}
		return instance.getCurrentSession();
	}

	public static void saveUserInDB(User user) {
		Session session = getInstance();
		try {

			session.beginTransaction();

			session.save(user);

			session.getTransaction().commit();

		} finally {
			getInstance().close();
		}

	}
	
	public static void updateUserPassword(int userId, String password) {
		Session session = getInstance();
		try {

			session.beginTransaction();

			User user = session.get(User.class, userId);
			user.setPassword(password);
			
			session.getTransaction().commit();

		} finally {
			getInstance().close();
		}

	}

	@SuppressWarnings("unchecked")
	public static List<User> queryUser(String query) {

		Session session = getInstance();
		List<User> theUsers;

		try {

			// start a transaction
			session.beginTransaction();

			// query students
			theUsers = session.createQuery(query).list();

			// commit transaction
			session.getTransaction().commit();

		} finally {
			getInstance().close();
		}
		return theUsers;
	}

	public static User getUserFromDB(int userId) {
		Session session = getInstance();
		User user;
		try {
			session.beginTransaction();

			user = session.get(User.class, userId);

			session.getTransaction().commit();
		} finally {
			getInstance().close();
		}
		return user;
	}

	public static void displayUsers(List<User> theUsers) {
		for (User tempUser : theUsers) {
			System.out.println(tempUser);
		}
	}

}
