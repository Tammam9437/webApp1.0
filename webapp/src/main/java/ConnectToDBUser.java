import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ConnectToDBUser {

	public static void saveUserInDB(User user) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			session.save(user);

			session.getTransaction().commit();

		} finally {
			factory.close();
		}

	}

	public static User getUserFromDB(int userId) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();
		User user;
		try {
			session.beginTransaction();

			
			user = session.get(User.class, userId);

			
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public static List<User> queryUser(String query) {
		// create session factory
				SessionFactory factory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(User.class)
										.buildSessionFactory();
				
				// create session
				Session session = factory.getCurrentSession();
				
				List<User> theUsers;
				
				try {			
					
					// start a transaction
					session.beginTransaction();
					
					// query students
					theUsers = session.createQuery(query).list();
					
					
					
										
					// commit transaction
					session.getTransaction().commit();
					
				}
				finally {
					factory.close();
				}
				return theUsers;
	}
	
	public static void displayUsers(List<User> theUsers) {
		for (User tempUser : theUsers) {
			System.out.println(tempUser);
		}
	}
	public static void main(String[] args) {

		List<User> theStudents = queryUser("From User");
		
		displayUsers(theStudents);

	}
}
