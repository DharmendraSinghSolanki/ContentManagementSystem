package com.niit.ContentManagementService.services;

import com.niit.ContentManagementService.domain.Content;
import com.niit.ContentManagementService.domain.User;
import com.niit.ContentManagementService.exceptions.ContentNotFoundException;
import com.niit.ContentManagementService.exceptions.UserAlreadyExistsException;
import com.niit.ContentManagementService.exceptions.UserNotFoundException;
import com.niit.ContentManagementService.proxy.UserProxy;
import com.niit.ContentManagementService.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService{

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private UserProxy userProxy;

    @Override
    public User addUser(User user) throws  UserAlreadyExistsException {
        if(contentRepository.findById(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        User savedUser = contentRepository.save(user);
        if (!(savedUser.getEmail().isEmpty())){
            ResponseEntity rs = userProxy.saveUser(user);
            System.out.println(rs.getBody());
        }
        return savedUser;
    }

    @Override
    public User addContentForUser(String email, Content content) throws UserNotFoundException {

        if (contentRepository.findById(email).isEmpty()){
            throw  new UserNotFoundException();
        }
        User user = contentRepository.findById(email).get();
        if (user.getContentList() == null){
            user.setContentList(Arrays.asList(content));
        }else {
            List<Content> contentList = user.getContentList();
            contentList.add(content);
            user.setContentList(contentList);
        }
        return contentRepository.save(user);
    }

    @Override
    public User deleteContentFromUser(String email, int postId) throws UserNotFoundException, ContentNotFoundException {
        boolean result = false;
        if (contentRepository.findById(email).isEmpty()){
            throw  new UserNotFoundException();
        }
        User user = contentRepository.findById(email).get();
        List<Content> contentList = user.getContentList();
        result = contentList.removeIf(x->x.getPostId()==postId);
        if (!result){
            throw  new ContentNotFoundException();
        }
        user.setContentList(contentList);
        return contentRepository.save(user);
    }

    @Override
    public List<Content> getContentsFormUser(String email) throws UserNotFoundException {
        if (contentRepository.findById(email).isEmpty()){
            throw new UserNotFoundException();
        }
        return contentRepository.findById(email).get().getContentList();
    }

    @Override
    public User updateContentForUser(String email, Content content) throws UserNotFoundException {
       if (contentRepository.findById(email).isEmpty()){
           throw  new UserNotFoundException();
       }
       User user = contentRepository.findById(email).get();
       List<Content> contentList = user.getContentList();
        for (Content content1:contentList) {
            if (content1.getPostId() == content.getPostId()){
                content1.setTitle(content.getTitle());
                content1.setContent(content.getContent());
                content1.setPostedBy(content.getPostedBy());
                content1.setPostedOn(content.getPostedOn());
            }
        }
        user.setContentList(contentList);
        return contentRepository.save(user);
    }

    @Override
    public Content getContentOfUser(String email, int postId) throws UserNotFoundException {
        if (contentRepository.findById(email).isEmpty()){
            throw  new UserNotFoundException();
        }
        User user = contentRepository.findById(email).get();
        List<Content> contentList = user.getContentList();
        for (Content content1:contentList) {
            if (content1.getPostId() == postId){
                return content1;
            }
        }
       return contentList.get(0);
    }
}
