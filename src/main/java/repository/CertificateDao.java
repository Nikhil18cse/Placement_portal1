package repository;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entities.Certificate;
import hibernatecfg.HibernateUtil;

public class CertificateDao {

	public void saveCertificate(Certificate certificate) {
	    Transaction transaction = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        session.save(certificate);
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null && transaction.isActive()) {
	            transaction.rollback();
	        }
	        // Log the exception
	        e.printStackTrace();
	        throw e;  // Re-throw the exception for higher-level handling
	    }
	}



    public Certificate getCertificate(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Certificate.class, id);
        }
    }

    public void updateCertificate(Certificate certificate) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(certificate);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
