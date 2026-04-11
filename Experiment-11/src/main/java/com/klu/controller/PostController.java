package com.klu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klu.model.Post;
import com.klu.service.PostService;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins="http://localhost:5173")
public class PostController {

    @Autowired
    private PostService service;

    @GetMapping
    public List<Post> getPosts(){
        return service.getPosts();
    }

    @PostMapping
    public Post addPost(@RequestBody Post post){
        return service.addPost(post);
    }
}