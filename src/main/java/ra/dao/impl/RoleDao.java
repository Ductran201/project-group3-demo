package ra.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ra.model.constrain.RoleName;
import ra.model.entity.Role;

@Repository
public class RoleDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Role getRoleName(RoleName roleName){
        Session session=sessionFactory.openSession();
        try {
            return session.createQuery("from Role where name= :name",Role.class)
                    .setParameter("name",roleName)
                    .getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }
}
