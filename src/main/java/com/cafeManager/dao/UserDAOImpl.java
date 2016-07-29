package com.cafeManager.dao;

import com.cafeManager.dto.RoleDTO;
import com.cafeManager.dto.UserDTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Martha on 7/29/2016.
 */
@Resource
@Transactional
public class UserDAOImpl implements UserDAO{

    @Autowired
    SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public UserDTO addUser(UserDTO user) {
        return null;
    }

    @Override
    public List<UserDTO> getAllUsersByRole(Long roleId) {
        return null;
    }

    @Override
    public RoleDTO addRole(RoleDTO role) {
        return null;
    }
}
