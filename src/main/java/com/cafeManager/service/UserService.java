package com.cafeManager.service;

import com.cafeManager.dto.RoleDTO;
import com.cafeManager.dto.UserDTO;

import java.util.List;

/**
 * Created by Martha on 7/29/2016.
 */
public interface UserService {

    /** Creates new user. Checks if user exist returns null, else returns created user. Checks if all arguments
     * exist. If any argument is missing throws EmptyArgumentException.*/
    UserDTO createUser(String username, String password, String role);

    /** Get users list with specified role. Checks if role is empty throws EmptyArgumentException, if role doesn't exist
     * returns null.*/
    List<UserDTO> getAllUsersByRole(String role);

    /** Creates new role. Checks if role is empty throws EmptyArgumentException, if role does exist
     * returns null.*/
    RoleDTO createRole(String role);

    /** Finds specified role. Checks if role is empty throws EmptyArgumentException, if role doesn't exist
     * returns null.*/
    RoleDTO getRole(String role);

}
