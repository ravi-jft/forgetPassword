package com.forgetPassword.dao;

import com.forgetPassword.domain.Users;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * Created by ravi on 9/5/17.
 */

public interface UserDao {
    public void addUser(Users user);
    public Users findByUsername(String username);
}

