package com.cafeManager.service;

import com.cafeManager.dao.UserDAO;
import com.cafeManager.dto.RoleDTO;
import com.cafeManager.dto.UserDTO;
import com.cafeManager.exception.*;
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
    public UserDTO createUser(String username, String password, String role) throws UserExistException, NoSuchRoleException, NullOrEmptyArgumentsException {
        if(username.isEmpty() || password.isEmpty() || role.isEmpty())throw new NullOrEmptyArgumentsException();
        RoleDTO roleDTO = userDAO.getRole(role);
        if(roleDTO == null)throw new NoSuchRoleException();
        if(userDAO.getUserByUsername(username) != null) throw new UserExistException();
        UserDTO userDTO = new UserDTO(username, password, roleDTO);
        return userDAO.addUser(userDTO);
    }

    @Override
    public UserDTO getUserById(String id) throws NoSuchUserException, NullOrEmptyArgumentsException {
        if(id == null || id.isEmpty()) throw new NullOrEmptyArgumentsException();
        UserDTO userDTO = userDAO.getUserById(Long.parseLong(id));
        if(userDTO == null)throw new NoSuchUserException();
        return userDTO;
    }

    @Override
    public UserDTO getUserByUsername(String username) throws NoSuchUserException, NullOrEmptyArgumentsException {
        if(username == null || username.isEmpty()) throw new NullOrEmptyArgumentsException();
        UserDTO userDTO = userDAO.getUserByUsername(username);
        if(userDTO == null)throw new NoSuchUserException();
        return userDTO;
    }

    @Override
    public List<UserDTO> getAllUsersByRole(String role) throws NoSuchRoleException, NoSuchUserException, NullOrEmptyArgumentsException{
        if(role == null || role.isEmpty())throw new NullOrEmptyArgumentsException();
        if(userDAO.getRole(role) == null) throw new NoSuchRoleException();
        List<UserDTO> users = userDAO.getAllUsersByRole(role);
        if(users.size() == 0) throw new NoSuchUserException();
        return userDAO.getAllUsersByRole(role);
    }

    @Override
    public RoleDTO createRole(String role) throws RoleExistException, NullOrEmptyArgumentsException {
        if(role == null || role.isEmpty()) throw new NullOrEmptyArgumentsException();
        if(userDAO.getRole(role) != null) throw new RoleExistException();
        RoleDTO roleDTO = new RoleDTO(role);
        return userDAO.addRole(roleDTO);
    }

    @Override
    public RoleDTO getRole(String role) throws NoSuchRoleException, NullOrEmptyArgumentsException{
        if(role == null || role.isEmpty()) throw new NullOrEmptyArgumentsException();
        RoleDTO roleDTO = userDAO.getRole(role);
        if(roleDTO == null)throw new NoSuchRoleException();
        return roleDTO;
    }
}
