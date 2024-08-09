package ra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.constrain.RoleName;
import ra.dao.impl.RoleDao;
import ra.dao.impl.UserDao;
import ra.model.dto.request.FormSignIn;
import ra.model.dto.request.FormSignUp;
import ra.model.entity.Role;
import ra.model.entity.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    public User signIn(FormSignIn formSignIn){
        return userDao.signIn(formSignIn.getEmail(),formSignIn.getPassword());
    }

    public boolean signUp(FormSignUp formSignUp){
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(roleDao.getRoleName(RoleName.ROLE_USER));
        User user = User.builder()
                .fullName(formSignUp.getFullName())
                .email(formSignUp.getEmail())
                .password(formSignUp.getPassword())
                .status(true)
                .isDelete(false)
                .createdDate(new Date())
                .roles(roleSet)
                .build();
        return userDao.signUp(user);
    }


}
