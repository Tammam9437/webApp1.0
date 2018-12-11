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

public class ConnectToCategoryDB {

	private static SessionFactory instance;

	private static Session getInstance() {
		if (instance == null) {
			instance = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
					.addAnnotatedClass(Pdf.class).addAnnotatedClass(Link.class).addAnnotatedClass(Image.class)
					.addAnnotatedClass(Email.class).addAnnotatedClass(Li.class).addAnnotatedClass(Category.class)
					.buildSessionFactory();
		}
		return instance.getCurrentSession();
	}

	public static void saveCategoryInDB(Category category) {
		Session session = getInstance();
		try {

			session.beginTransaction();

			session.save(category);

			session.getTransaction().commit();

		} finally {
			getInstance().close();
		}

	}
	

	public static Category getCategoryFromDB(int idCategory) {
		Session session = getInstance();
		Category category;
		try {
			session.beginTransaction();

			category = session.get(Category.class, idCategory);

			session.getTransaction().commit();
		} finally {
			getInstance().close();
		}
		return category;
	}

	@SuppressWarnings("unchecked")
	public static List<Category> queryCategory(String query) {

		Session session = getInstance();
		List<Category> theCategorys;

		try {

			// start a transaction
			session.beginTransaction();

			// query students
			theCategorys = session.createQuery(query).list();

			// commit transaction
			session.getTransaction().commit();

		} finally {
			getInstance().close();
		}
		return theCategorys;
	}

	public static void displayCategorys(List<Category> theCategorys) {
		for (Category tempCategory : theCategorys) {
			System.out.println(tempCategory);
		}
	}

}
