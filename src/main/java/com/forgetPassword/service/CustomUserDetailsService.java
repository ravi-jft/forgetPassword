package com.forgetPassword.service;

import com.forgetPassword.dao.UserDao;
import com.forgetPassword.domain.Roles;
import com.forgetPassword.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravi on 10/5/17.
 */
@Component
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user =  userDao.findByUsername(username);
        System.out.println("========user========"+user);
      /*return new User(s, user.getPassword(),user.getRoleses());*/

       /* if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }*/
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), user.getEnabled(),
                true, true, !user.getLocked(), getAuthorities(user));
    }

    private List<GrantedAuthority> getAuthorities(Users user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (Roles role : user.getRoleses()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }
}
