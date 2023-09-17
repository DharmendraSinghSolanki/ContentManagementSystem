package com.niit.authentication.services;

import com.niit.authentication.domain.User;
import com.niit.authentication.exceptions.UserAlreadyExistsException;
import com.niit.authentication.exceptions.UserNotFoundException;

public interface UserService {

    User addUser(User user) throws UserAlreadyExistsException;
    User getUserByEmailAndPassword(String email,String password) throws UserNotFoundException;
}
