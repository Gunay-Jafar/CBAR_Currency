/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isb.config;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author User
 */
public class HibernateUtilCbar {

    private HibernateUtilCbar() {
    }
    
    public static SessionFactory getInstance(){
        return HibernateUtilHelper.FACTORY;
    }
    
    private static class HibernateUtilHelper{
        private static volatile SessionFactory FACTORY = new Configuration().configure("/isb/config/hibernate.cfg.xml").buildSessionFactory();
    }
    
     public static void close(Session session) {
        if (session != null) {
            session.close();
        }
    }

    public static void rollBack(Transaction transaction) {
        if (transaction != null) {
            transaction.rollback();
        }
    }
}
