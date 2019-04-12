package managers;

import java.util.List;
import java.util.Date;
import java.util.Iterator;

import entities.Profile;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;

public class ProfileManager {
    public static SessionFactory factory = null;
    public static final ProfileManager PROFILE_MANAGER = new ProfileManager();

    public static void init() throws HibernateException{
        factory = new Configuration().configure().buildSessionFactory();
    }
//    public static void main(String[] args) {
//
//        try {
//            factory = new Configuration().configure().buildSessionFactory();
//        } catch (Throwable ex) {
//            System.err.println("Failed to create sessionFactory object." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//
//        ProfileManager pm = new ProfileManager();
//
//        /* Add few profile records in database */
//        Integer empID1 = pm.addProfile(0,"Vasya Pupkin", "AGTAGTTASGGASDASD", "###############.##..##");
//
//        /* ListProfiles down all the profiles */
//        pm.listProfiles();
//
//    }

    /* Method to CREATE an profile in the database */
    public Integer addProfile(Integer id, String name, String dna, String fingerPrint){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer profileID = null;

        try {
            tx = session.beginTransaction();
            Profile profile;
            if(id != null)
                profile = new Profile(id, name, dna, fingerPrint);
            else
                profile = new Profile(name, dna, fingerPrint);
            profileID = (Integer) session.save(profile);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return profileID;
    }

    public boolean isExist(Integer ProfileID){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Profile profile = (Profile)session.get(Profile.class, ProfileID);
            tx.commit();
            return profile != null;
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    /* Method to  READ all the profiles */
    public void listProfiles(int n){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List profiles = session.createQuery("FROM Profile f ORDER BY f.id").setMaxResults(n).list();
            for (Iterator iterator = profiles.iterator(); iterator.hasNext();){
                Profile profile = (Profile) iterator.next();
                System.out.print("Id: " + profile.getId() + ";");
                System.out.print("Name: " + profile.getName() + ";");
                System.out.print("DNA: " + profile.getDna() + ";");
                System.out.println();
                System.out.println("Finger print: " + profile.getFingerprint());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to UPDATE salary for an profile */
    public void updateProfile(Integer ProfileID, String name, String dna, String fingerPrint ){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Profile profile = (Profile)session.get(Profile.class, ProfileID);
            profile.setName( name );
            profile.setDna(dna);
            profile.setFingerprint(fingerPrint);
            session.update(profile);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to DELETE an profile from the records */
    public void deleteProfile(Integer ProfileID){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Profile profile = (Profile)session.get(Profile.class, ProfileID);
            session.delete(profile);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}