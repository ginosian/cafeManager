package com.cafeManager.service;

import com.cafeManager.dao.UserDAO;
import com.cafeManager.dto.RoleDTO;
import com.cafeManager.dto.UserDTO;
import com.cafeManager.exception.EmptyArgumentsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Martha on 7/29/2016.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDAO userDAO;

    @Override
    public UserDTO createUser(String username, String password, String role) {
        if(username.isEmpty() || password.isEmpty() || role.isEmpty())throw new EmptyArgumentsException();
        RoleDTO roleDTO = userDAO.getRole(role);
        if(userDAO.getUser(username) != null || roleDTO == null) return null;
        UserDTO userDTO = new UserDTO(username, password, roleDTO);
        return userDAO.addUser(userDTO);
    }

    @Override
    public List<UserDTO> getAllUsersByRole(String role) {
        if(role.isEmpty())throw new EmptyArgumentsException();
        if(userDAO.getRole(role) == null) return null;
        return userDAO.getAllUsersByRole(role);
    }

    @Override
    public RoleDTO createRole(String role) {
        if(role.isEmpty()) throw new EmptyArgumentsException();
        if(userDAO.getRole(role) != null) return null;
        RoleDTO roleDTO = new RoleDTO(role);
        return userDAO.addRole(roleDTO);
    }

    @Override
    public RoleDTO getRole(String role) {
        if(role.isEmpty()) throw new EmptyArgumentsException();
        return userDAO.getRole(role);
    }
}
