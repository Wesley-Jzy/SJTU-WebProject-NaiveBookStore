package com.easybookstore.auth;

import com.easybookstore.DAO.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 控制登录逻辑和角色分配
 */
public class LoginManager implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("User: " + username + " try to login");

        // find user with username
        com.easybookstore.entity.User user = userDAO.getInstanceByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("用户不存在!");
        }

        // set authority
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
        if(user.getIsAdmin() == 1) {
            setAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        setAuths.add(new SimpleGrantedAuthority("ROLE_USER"));

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(setAuths);

        return new User(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
    }
}
