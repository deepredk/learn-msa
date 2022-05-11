package com.example.mongodbapisample.entity;

import org.springframework.data.annotation.Id;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Comment {
    @Id
    public String id;
    
    public String comment;

    public Comment(String comment) {
        this.comment = comment;
    }
}
