package com.cafeManager.dao;

import com.cafeManager.dto.RoleDTO;
import com.cafeManager.dto.UserDTO;

import java.util.List;

/**
 * Created by Martha on 7/29/2016.
 */
public interface UserDAO {

    /** Creates new user.*/
    UserDTO addUser(UserDTO user);

    /** Finds specified user by username*/
    UserDTO getUser(String username);

    /** Get users list with specified role.*/
    List<UserDTO> getAllUsersByRole(String role);

    /** Creates new role.*/
    RoleDTO addRole(RoleDTO role);

    /** Finds specified role*/
    RoleDTO getRole(String role);
}
