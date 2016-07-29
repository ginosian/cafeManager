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

    /** Get users list with specified role.*/
    List<UserDTO> getAllUsersByRole(Long roleId);

    /** Creates new role.*/
    RoleDTO addRole(RoleDTO role);
}
