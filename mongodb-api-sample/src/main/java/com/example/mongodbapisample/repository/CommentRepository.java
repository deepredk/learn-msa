package com.example.mongodbapisample.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.mongodbapisample.entity.Comment;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    
}
