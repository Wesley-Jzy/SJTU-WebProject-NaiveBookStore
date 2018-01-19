package com.easybookstore.DAO;

import com.easybookstore.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wesley on 17/4/19.
 */

@Repository
public class UserDAOImpl implements UserDAO  {

    @Autowired
    private SessionFactory sessionFactory;

    public User getInstanceByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select u from users u where u.username = :username");
        query.setString("username", username);

        List<User> users = ((List<User>) query.list());

        if (users.isEmpty()) {
            return null;
        } else {
            return users.get(0);
        }
    }

    public List<User> getListWithoutAdmin(int offset, int rows) {
        Session session = sessionFactory.getCurrentSession();
        List<User> users = (List<User>) (session.createQuery("select u from users u where u.isAdmin <> 1")
                .setFirstResult(offset)
                .setMaxResults(rows)
                .list());

        return users;
    }

    public long getCount() {
        Session session = sessionFactory.getCurrentSession();
        long count = (long)(Long)(session.createQuery("select count(*) from users")
                    .uniqueResult());
        return count;
    }

    public void createOrUpdate(User user) {
        if (user == null) {
            System.out.println("null user");
            return;
        }
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
        session.flush();
        return;
    }

    public void delete(long id) {

        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, id);
        session.delete(user);
        session.flush();
        return;
    }

    public User getInstanceById(long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, id);
        return user;
    }

}
