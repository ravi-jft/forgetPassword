package com.forgetPassword.dao;

import com.forgetPassword.domain.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * Created by ravi on 9/5/17.
 */
public class RoleDaoImpl implements RoleDao {

    @Autowired
    HibernateTemplate hibernateTemplate;
    @Override
    public void addRole(Roles role) {
        hibernateTemplate.save(role);
    }
}
