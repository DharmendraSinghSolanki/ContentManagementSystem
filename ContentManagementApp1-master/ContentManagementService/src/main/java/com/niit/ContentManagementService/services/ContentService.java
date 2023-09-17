package com.niit.ContentManagementService.services;

import com.niit.ContentManagementService.domain.Content;
import com.niit.ContentManagementService.domain.User;
import com.niit.ContentManagementService.exceptions.ContentNotFoundException;
import com.niit.ContentManagementService.exceptions.UserAlreadyExistsException;
import com.niit.ContentManagementService.exceptions.UserNotFoundException;

import java.util.List;

public interface ContentService {

    User addUser(User user) throws  UserAlreadyExistsException;
    User addContentForUser(String email, Content content) throws UserNotFoundException;
    User deleteContentFromUser(String email,int postId) throws UserNotFoundException, ContentNotFoundException;
    List<Content> getContentsFormUser(String email) throws UserNotFoundException;
    User updateContentForUser(String email,Content content) throws UserNotFoundException;

    Content getContentOfUser(String email,int postId) throws UserNotFoundException;
}
