package com.easybookstore.DAO;

import com.easybookstore.entity.User;
import java.util.List;

/**
 * Created by wesley on 17/4/19.
 */
public interface UserDAO {
    public User getInstanceByUsername(String username);
    public long getCount();
    public List<User> getListWithoutAdmin(int offset, int rows);
    public void createOrUpdate(User user);
    public void delete(long id);
    public User getInstanceById(long id);
}
