package ra.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.dao.IActorDao;
import ra.model.entity.Actor;

import java.util.List;

@Repository

public class ActorDaoImpl implements IActorDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public String getImageById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            return (String) session.createQuery("select act.image from Actor act where act.id = :id")
                    .setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Actor> findAll() {
        Session session = sessionFactory.openSession();
        return session.createQuery("from Actor ", Actor.class).list();
    }

    @Override
    public Actor findById(Long id) {
        Session session = sessionFactory.openSession();
        return session.find(Actor.class, id);
    }

    @Override
    public void save(Actor actor) {
        Session session = sessionFactory.openSession();
        if (actor.getId() != null) {
            session.update(actor);
        } else {
            session.save(actor);
        }
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        session.delete(findById(id));
    }
}
