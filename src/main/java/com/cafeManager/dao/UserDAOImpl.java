package com.cafeManager.dao;

import com.cafeManager.dto.RoleDTO;
import com.cafeManager.dto.UserDTO;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Martha on 7/29/2016.
 */
@Repository
@Transactional
public class UserDAOImpl implements UserDAO{

    @Autowired
    SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public UserDTO addUser(UserDTO user) {
        Session session = getSession();
        try{
            session.save(user);
            return user;
        } catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserDTO getUserById(Long userId) {
        Session session = getSession();
        try{
            Query query = session.createQuery("from UserDTO userDTO where userDTO.id = :id");
            query.setParameter("id", userId);
            if (query.list().size() < 1) return null;
            UserDTO userDTO = (UserDTO)query.list().get(0);
            return userDTO;
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserDTO getUserByUsername(String userName) {
        Session session = getSession();
        try{
            Query query = session.createQuery("from UserDTO userDTO where userDTO.username = :username");
            query.setParameter("username", userName);
            if (query.list().size() < 1) return null;
            UserDTO userDTO = (UserDTO)query.list().get(0);
            return userDTO;
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<UserDTO> getAllUsersByRole(String role) {
        Session session = getSession();
        try{
            Query query = session.createQuery("from UserDTO u where u.role.role = :role");
            query.setParameter("role", role);
            List<UserDTO> users = query.list();
            if (users.size() < 1) return null;
            return users;
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public RoleDTO addRole(RoleDTO role) {
        Session session = getSession();
        try{
            session.save(role);
            return role;
        } catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public RoleDTO getRole(String role) {
        Session session = getSession();
        try{
            Query query = session.createQuery("from RoleDTO roleDTO where roleDTO.role = :role");
            query.setParameter("role", role);
            if (query.list().size() < 1) return null;
            RoleDTO roleDTO = (RoleDTO)query.list().get(0);
            return roleDTO;
        }
        catch (HibernateException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addRememberMeTable() {
        Session session = getSession();
        try {
        String remTableQuery = "CREATE TABLE IF NOT EXISTS persistent_logins ( " +
                                        "username VARCHAR(64) NOT NULL, " +
                                        "series VARCHAR(64) NOT NULL, " +
                                        "token VARCHAR(64) NOT NULL, " +
                                        "last_used TIMESTAMP NOT NULL, " +
                                        "PRIMARY KEY (series))";
            session.createSQLQuery(remTableQuery).executeUpdate();
        } catch (HibernateException e){
            e.printStackTrace();
        }
    }
}
