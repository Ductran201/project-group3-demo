package ra.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.dao.ICategoryDao;
import ra.model.entity.Category;

import java.util.List;

@Repository
public class CategoryDaoImpl implements ICategoryDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Category> findAll() {
        Session session = sessionFactory.openSession();
        return session.createQuery("from Category ", Category.class).list();
    }

    @Override
    public Category findById(Integer id) {
        Session session = sessionFactory.openSession();
        return session.find(Category.class, id);
    }

    @Override
    public void save(Category category) {
        Session session = sessionFactory.openSession();
        try {
            if (category.getId() != null) {
                session.update(category);
            } else {
                category.setStatus(true);
                session.save(category);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }

    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        session.delete(findById(id));
    }
}
