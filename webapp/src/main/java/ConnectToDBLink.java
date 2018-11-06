import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ConnectToDBLink {

	public static void saveLinkInDB(Link link) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Link.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			session.save(link);

			session.getTransaction().commit();

		} finally {
			factory.close();
		}

	}

	public static Link getLinkFromDB(int linkId) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Link.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();
		Link user;
		try {
			session.beginTransaction();

			
			user = session.get(Link.class, linkId);

			
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Link> queryLink(String query) {
		// create session factory
				SessionFactory factory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Link.class)
										.buildSessionFactory();
				
				// create session
				Session session = factory.getCurrentSession();
				
				List<Link> theLinks;
				
				try {			
					
					// start a transaction
					session.beginTransaction();
					
					// query students
					theLinks = session.createQuery(query).list();
					
					
					
										
					// commit transaction
					session.getTransaction().commit();
					
				}
				finally {
					factory.close();
				}
				return theLinks;
	}
	
	public static void displayLinks(List<Link> theLinks) {
		for (Link tempLink : theLinks) {
			System.out.println(tempLink);
		}
	}
	public static void main(String[] args) {

		List<Link> theLinks = queryLink("From Link");
		
		displayLinks(theLinks);

	}
}
