package com.niit.ContentManagementService.controller;

import com.niit.ContentManagementService.domain.Content;
import com.niit.ContentManagementService.domain.User;
import com.niit.ContentManagementService.exceptions.ContentNotFoundException;
import com.niit.ContentManagementService.exceptions.UserAlreadyExistsException;
import com.niit.ContentManagementService.exceptions.UserNotFoundException;
import com.niit.ContentManagementService.services.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/contentManagement/v1/api/")
@CrossOrigin("*")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody User user) throws UserAlreadyExistsException {
        ResponseEntity responseEntity=null;
        try{
            user.setContentList(new ArrayList<>());
            responseEntity=new ResponseEntity<>(contentService.addUser(user), HttpStatus.CREATED);
        }catch (UserAlreadyExistsException e){
            throw new UserAlreadyExistsException();
        }catch (Exception e){
            responseEntity=new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("/content/{email}")
    public ResponseEntity<?> addContentForUser(@PathVariable String email, @RequestBody Content content){
        ResponseEntity<?> responseEntity =  null;
        try{
            responseEntity = new ResponseEntity<>(contentService.addContentForUser(email,content),HttpStatus.OK);
        } catch (UserNotFoundException e) {
            responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("/content/delete/{postId}/{email}")
    public ResponseEntity<?> deleteContentFormUser(@PathVariable int postId,@PathVariable String email) throws UserNotFoundException, ContentNotFoundException {
        ResponseEntity<?> responseEntity = null;
        try{
            responseEntity = new ResponseEntity<>(contentService.deleteContentFromUser(email, postId),HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (ContentNotFoundException e) {
            throw new ContentNotFoundException();
        }catch (Exception e){
            responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @GetMapping("content/allContent/{email}")
    public ResponseEntity<?> getAllContentsForUser(@PathVariable String email) throws UserNotFoundException {
        ResponseEntity responseEntity = null;
        try {
            responseEntity = new ResponseEntity(contentService.getContentsFormUser(email),HttpStatus.OK);
        } catch (UserNotFoundException e) {
           responseEntity = new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PutMapping("content/updateContent/{email}")
    public ResponseEntity<?> updateContentForUser(@PathVariable String email,@RequestBody Content content) throws UserNotFoundException {
        ResponseEntity responseEntity = null;
        try {
            responseEntity = new ResponseEntity(contentService.updateContentForUser(email, content),HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw  new UserNotFoundException();
        }catch (Exception e){
            responseEntity = new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @GetMapping("content/contentByPost/{email}/{postId}")
    public ResponseEntity<?> getSpecificContentsForUser(@PathVariable String email,@PathVariable int postId) throws UserNotFoundException {
        ResponseEntity responseEntity = null;
        try {
            responseEntity = new ResponseEntity(contentService.getContentOfUser(email, postId),HttpStatus.OK);
        } catch (UserNotFoundException e) {
            responseEntity = new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
}
