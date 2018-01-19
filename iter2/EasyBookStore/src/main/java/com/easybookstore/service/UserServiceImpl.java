package com.easybookstore.service;

import com.easybookstore.DAO.UserDAO;
import com.easybookstore.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wesley on 17/4/19.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    public List<User> getListWithoutAdmin(int offset, int rows) {
        return userDAO.getListWithoutAdmin(offset, rows);
    }

    public long getCount() {
        return userDAO.getCount();
    }

    public void createOrUpdate(User user) {
        userDAO.createOrUpdate(user);
    }

    public void delete(long id) {
        userDAO.delete(id);
    }
}
