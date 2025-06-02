package com.app.infrastructure.repository.impl;

import com.app.infrastructure.repository.PostRepository;
import com.app.infrastructure.repository.entity.PostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl {

    @Autowired
    private PostRepository repository;


    public PostEntity findById(String id) {
        PostEntity post = repository.findById(id).orElse(null);
        if (post == null) {
            throw new RuntimeException("User not found with id: " + id);
        }
        return post;
    }

    public List<PostEntity> findByTitle(String text) {
        return repository.suorcgTitle(text);
    }

}

