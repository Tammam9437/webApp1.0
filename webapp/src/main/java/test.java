
public class test {
	
		private static Session getInstance() {
				if (instance == null) {
					instance = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(UserData.class)
							.addAnnotatedClass(MainProductCategory.class).addAnnotatedClass(SubProductCategory.class)
							.addAnnotatedClass(Product.class).buildSessionFactory();
				}
				return instance.getCurrentSession();
			} 
		public static Product getProductForId(int productId) {
				getInstance().beginTransaction();

				CriteriaQuery<Product> criteriaQuery = getInstance().getCriteriaBuilder().createQuery(Product.class);

				Root<Product> e = criteriaQuery.from(Product.class);

				List<Predicate> predicates = new ArrayList<Predicate>();
				predicates.add(getInstance().getCriteriaBuilder().equal(e.get("id"), productId));

				criteriaQuery.select(e).where(predicates.toArray(new Predicate[] {}));

				Query<Product> query = getInstance().createQuery(criteriaQuery);
				ArrayList<Product> result = (ArrayList<Product>) query.getResultList();

				getInstance().close();

				if (result != null && result.size() != 0) {
					return result.get(0);
				}
				return null;
			} 

}
