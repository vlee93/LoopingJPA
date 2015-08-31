package customTools;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.DemoCustomer;

public class DBLoop {

	public static List<DemoCustomer> getCustomers()
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		List<DemoCustomer> customers = null;
		String qString = "select b from DemoCustomer b";
		try{
			Query q = em.createQuery(qString);
			customers = q.getResultList();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			em.close();
		}return customers;
	}

	public static DemoCustomer getCust(long userID)
	{
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		DemoCustomer cust = em.find(DemoCustomer.class, userID);
		return cust;		
	}
	
}
