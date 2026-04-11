package com.klu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.model.Post;
import com.klu.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    public List<Post> getPosts(){
        return repo.findAll();
    }

    public Post addPost(Post post){
        return repo.save(post);
    }
}