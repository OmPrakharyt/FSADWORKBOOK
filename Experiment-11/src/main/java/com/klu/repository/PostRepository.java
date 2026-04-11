package com.klu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.klu.model.Post;

public interface PostRepository extends JpaRepository<Post,Integer>{

}