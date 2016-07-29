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
    public UserDTO getUser(String username) {
        Session session = getSession();
        try{
            Query query = session.createQuery("from UserDTO userDTO where userDTO.username = :username");
            query.setParameter("username", username);
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
            Query query = session.createQuery("select UserDTO from UserDTO userDTO where userDTO.role = :role");
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
}
