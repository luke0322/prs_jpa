package business;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import db.DBUtil;

public class PurchaseRequestDB {
	public static PurchaseRequest getPurchaseRequestById(int purchId) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			PurchaseRequest purch = em.find(PurchaseRequest.class, purchId);
			return purch;
		}finally{ //no exception present, close the connection after finishing
			em.close();
		}
	}
	public static boolean addPurchaseRequest(PurchaseRequest p) {
		boolean success = false;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(p);
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

}
