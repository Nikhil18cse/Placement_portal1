package repository;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import entities.AppliedJob;
import entities.Placement;
import hibernatecfg.HibernateUtil;

public class PlacementDao {

    // Save Placement Details
	public void savePlacement(Placement placement) {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        Transaction transaction = session.beginTransaction();
	        session.save(placement);
	        transaction.commit();
	    } catch (Exception e) {
	        e.printStackTrace();  // Print stack trace to logs
	        throw new RuntimeException("Error saving placement: " + e.getMessage(), e);
	    }
	}

    // Update Placement Details
    public void updatePlacement(Placement placement) {
        executeTransaction(session -> session.update(placement));
    }

    // Delete Placement Details
    public void deletePlacement(int id) {
        executeTransaction(session -> {
            Placement placement = session.get(Placement.class, id);
            if (placement != null) {
                session.delete(placement);
            }
        });
    }

    // Fetch All Placements
    @SuppressWarnings("unchecked")
    public List<Placement> getAllPlacement() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Placement").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Apply for Job
    public boolean applyForJob(int userId, int jobId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Create AppliedJob object
            AppliedJob appliedJob = new AppliedJob();
            appliedJob.setUserId(userId);
            appliedJob.setJobId(jobId);

            // Save the applied job to the database
            session.save(appliedJob);

            // Commit the transaction
            transaction.commit();
            return true;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    // Utility method to execute transactions
    private void executeTransaction(HibernateTransactionAction action) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            action.execute(session);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @FunctionalInterface
    private interface HibernateTransactionAction {
        void execute(Session session);
    }
}
