package com.niit.ContentManagementService.repository;

import com.niit.ContentManagementService.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContentRepository extends MongoRepository<User,String> {
}
