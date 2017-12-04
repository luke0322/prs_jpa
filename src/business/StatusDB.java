package business;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import javax.persistence.TypedQuery;

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
	public static ArrayList<Status> getAllStatuses(){ //gets them by id, description
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			TypedQuery<Status> query = em.createQuery("SELECT s FROM Status s",Status.class); //JPQL
			ArrayList<Status> allStatuses = new ArrayList<>(query.getResultList());
			return allStatuses;
		}finally{ //no exception present, close the connection after finishing
			em.close();
		}
	}


	
}
