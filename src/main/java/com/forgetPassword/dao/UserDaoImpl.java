package com.forgetPassword.dao;

import com.forgetPassword.domain.Users;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

/**
 * Created by ravi on 9/5/17.
 */

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void addUser(Users user) {
        hibernateTemplate.save(user);
    }

    @Override
    public Users findByUsername(String username) {
        return (Users) hibernateTemplate.findByCriteria(
                DetachedCriteria.forClass(Users.class)
                .add(Restrictions.eq("username", username)));
    }
}
