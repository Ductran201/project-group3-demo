package ra.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.model.entity.User;

import java.util.List;

@Repository
public class UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    //
    public User signIn(String email, String password) {
        // Sign in <- check match account in database
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from User where email =: email and password= :password", User.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public boolean signUp(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;

    }

    public List<User> findAll(){
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from User", User.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

}
