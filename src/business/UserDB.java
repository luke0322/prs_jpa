package business;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import db.DBUtil;

public class UserDB {
	
	public static ArrayList<User> getAllUsers(){
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u",User.class); //JPQL
			ArrayList<User> allUsers = new ArrayList<>(query.getResultList());
			return allUsers;
		}finally{ //no exception present, close the connection after finishing
			em.close();
		}
	}
	public static boolean deleteUser(User u) {
		boolean success = false;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.remove(em.merge(u));
			trans.commit();
			success = true;
			
		}catch(Exception e) {
			e.printStackTrace();
			trans.rollback();
		}finally {
			em.close();
		}
		return success;
	}
	public static boolean addUser(User u) {
		boolean success = false;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(u);
			trans.commit();
			success = true;
			
		}catch(Exception e) {
			e.printStackTrace();
			trans.rollback();
		}finally {
			em.close();
		}
		return success;
	}
	
	public static boolean updateUser(User u) {
		boolean success = false;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(u);
			trans.commit();
			success = true;
			
		}catch(Exception e) {
			e.printStackTrace();
			trans.rollback();
		}finally {
			em.close();
		}
		return success;
	}
	public static User authenticateUser(String userName,String password) {
		
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String jpql = "Select u from User u where u.userName = :uname"; 
		//parameterized queries needed for less risk of SQL injection attacks, uname is the ? like in JDBC
		TypedQuery<User> q = em.createQuery(jpql,User.class);
		q.setParameter("uname", userName);
		//q.setParameter("pwd", password);
		User usr = null;
		try {
		usr = q.getSingleResult();
		}
		catch(NoResultException nre) {
			System.out.println("No user found for username " + userName + " and pwd " + password);
			nre.printStackTrace();
		}finally {
			em.close();
		}
		return usr;
	}
	
	public static User getUserById(int userId) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			User user = em.find(User.class, userId);
			return user;
		}finally{ //no exception present, close the connection after finishing
			em.close();
		}
	}
//	public static User getUserByUsername(String userName) { //use a typed query
//		EntityManager em = DBUtil.getEmFactory().createEntityManager();
//		try {
//			User user = em.(User.class, userName);
//			return user;
//		}finally{ //no exception present, close the connection after finishing
//			em.close();
//		}
//	}

	


}
