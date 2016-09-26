package ssa;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class HibernateConnector {
	
	private SessionFactory factory;
	private Session session;
	private Transaction tx;
	
	public void close() {
		if (this.session!=null) 
			this.session.close();
		this.factory.close();
	}
	
	@SuppressWarnings("unchecked")
	public int getId(String description) {
		session=factory.openSession();
		List<Major> majors = session.createCriteria(Major.class).add(Restrictions.like("description", description)).list();
		if (majors.isEmpty())
			return -1;
		else
			return majors.get(0).getId();
	}
	
	public String toString() {
		String sb = "";
		for (Major major : this.getAllMajors()) {
			sb += major.toString() + '\n';
		}
		return sb;
	}
	
	public int insertMajor(Major major) {
		session = this.getCurrentSession();
		session.beginTransaction();
		session.save(major);
		System.out.println("Inserting Major: " + major);
		session.getTransaction().commit();
		return major.getId();
	}
	
	public void updateMajor(int majorId, String newDescription) {
		Major major = this.getMajor(majorId);
		System.out.println("Updating Major: " + major + " to");
		major.setDescription(newDescription);
		this.updateMajor(major);
	}
	
	public void updateMajor(int majorId, int newReqSat) {
		Major major = this.getMajor(majorId);
		System.out.println("Updating Major: " + major + " to");
		major.setReqSat(newReqSat);
		this.updateMajor(major);
	}
	
	public void updateMajor(int majorId, String newDescription, int newReqSat) {
		Major major = this.getMajor(majorId);
		System.out.println("Updating Major: " + major + " to");
		major.setDescription(newDescription);
		major.setReqSat(newReqSat);
		this.updateMajor(major);
	}
	
	public void deleteMajor(int majorId) {
		Major major = this.getMajor(majorId);
		System.out.println("Deleting Major: " + major);
		session.delete(major);
		tx.commit();
	}
	
	@SuppressWarnings("unchecked")
	private List<Major> getAllMajors() {
		session=factory.openSession();
		return session.createCriteria(Major.class).list();
	}
	
	private Major getMajor(int majorId) {
		session = factory.openSession();
		tx = session.beginTransaction();
		return session.get(Major.class, majorId);
	}
	
	private void updateMajor(Major major) {
		session.update(major);
		System.out.println("After Update: " + major);
		tx.commit();
	}
	
	private Session getCurrentSession() {
		return this.factory.getCurrentSession();
	}
	
	public HibernateConnector() {
		this.factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Major.class).buildSessionFactory();
		session = null;
		tx = null;
	}
}
