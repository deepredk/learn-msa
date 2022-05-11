package com.example.mongodbapisample.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mongodbapisample.entity.Comment;
import com.example.mongodbapisample.repository.CommentRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentRepository commentRepo;

    @GetMapping("/comments")
    public List<Comment> viewComments() {
        return commentRepo.findAll();
    }

    @PostMapping("/comments")
    public Comment writeComment(@RequestParam("comment") String comment) {
        Comment commentEntity = new Comment(comment);
        return commentRepo.save(commentEntity);
    }
}
