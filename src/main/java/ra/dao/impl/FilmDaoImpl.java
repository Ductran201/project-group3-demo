package ra.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.dao.IFilmDao;
import ra.model.entity.Film;

import java.util.List;

@Repository
public class FilmDaoImpl implements IFilmDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Film> findAll() {
        Session session = sessionFactory.openSession();
        return session.createQuery("from Film", Film.class).list();
    }

    @Override
    public Film findById(Integer id) {
        Session session = sessionFactory.openSession();
        return session.find(Film.class, id);
    }


    public void save(Film film) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        try{
            if (film.getId() != null) {
                session.update(film);
            } else {
                session.save(film);
            }
            transaction.commit();
        }catch (Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
        session.close();
        }

    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        session.delete(findById(id));
    }

    @Override
    public String getImageById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            return (String) session.createQuery("select f.image from Film f where f.id = :id")
                    .setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }
}
