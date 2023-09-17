package com.niit.authentication.controller;

import com.niit.authentication.domain.User;
import com.niit.authentication.exceptions.UserAlreadyExistsException;
import com.niit.authentication.exceptions.UserNotFoundException;
import com.niit.authentication.services.SecurityTokenGenerator;
import com.niit.authentication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("user/v1/")
@CrossOrigin("*")
public class UserController {

    private UserService userService;

    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator){
        this.userService=userService;
        this.securityTokenGenerator=securityTokenGenerator;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws UserNotFoundException {
        Map<String,String> map=null;
        try{
            User user1=userService.getUserByEmailAndPassword(user.getEmail(), user.getPassword());
            if(user1.getEmail().equals(user.getEmail())){
                map=securityTokenGenerator.generateToken(user);
            }
            return new ResponseEntity<>(map, HttpStatus.OK);
        }catch (UserNotFoundException e){
            throw new RuntimeException(e);
        }catch (Exception e){
            return new ResponseEntity<>("Try after sometimes", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user)throws UserAlreadyExistsException {
        User userCreated=userService.addUser(user);
        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }


}
