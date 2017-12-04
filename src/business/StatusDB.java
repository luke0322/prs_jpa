package business;

import javax.persistence.EntityManager;

import db.DBUtil;

public class StatusDB {
	
	public static Status getStatusById(int statusId) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			Status stat = em.find(Status.class, statusId);
			return stat;
		}finally{ //no exception present, close the connection after finishing
			em.close();
		}
	}
}
