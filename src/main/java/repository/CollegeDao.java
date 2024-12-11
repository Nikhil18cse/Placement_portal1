package repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.College;
import hibernatecfg.HibernateUtil;


public class CollegeDao {
	
	//Save College Details
	public void saveCollege(College college) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the college object
			session.save(college);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	//Update College Details
	 
	public void updateCollege(College college) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the college object
			session.update(college);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	//Delete College Details
	public void deleteCollege(int id) {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// Delete a college object
			College college = session.get(College.class, id);
			if (college != null) {
				session.delete(college);
				System.out.println("college is deleted");
			}

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	//Display  Specific College Details
	public College getCollege(int id) {

		Transaction transaction = null;
		College college = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an college object
			college = session.get(College.class, id);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return college;
	}
	
	//Display  ALL College Details
	@SuppressWarnings("unchecked")
	public List<College> getAllCollege() {

		Transaction transaction = null;
		List<College> listOfCollege = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an college object
			
			listOfCollege = session.createQuery("from College").getResultList();
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listOfCollege;
	}
}
