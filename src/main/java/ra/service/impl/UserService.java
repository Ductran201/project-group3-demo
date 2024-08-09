package ra.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.dao.impl.UserDao;
import ra.model.entity.User;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public List<User> findAll(){
        return userDao.findAll();
    }


}
