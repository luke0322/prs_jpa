package db;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import business.Product;
import business.User;
import db.DBUtil;

public class ProductDB {
	//private static ArrayList<Product> products = null;
	
	public ProductDB() {
	}

	public static Product getProductById(int vendorId) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			Product p = em.find(Product.class, vendorId);
			return p;
		}finally{ //no exception present, close the connection after finishing
			em.close();
		}
	}
	public static ArrayList<Product> getAllProducts(){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p",Product.class); //JPQL
			ArrayList<Product> allProducts = new ArrayList<>(query.getResultList());
			return allProducts;
		}finally{ //no exception present, close the connection after finishing
			em.close();
		}
	}
}