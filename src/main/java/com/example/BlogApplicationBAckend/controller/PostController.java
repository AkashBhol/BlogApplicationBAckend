package com.example.BlogApplicationBAckend.controller;

import com.example.BlogApplicationBAckend.DTO.PageableDTO;
import com.example.BlogApplicationBAckend.DTO.PostDTO;
import com.example.BlogApplicationBAckend.DTO.Result;
import com.example.BlogApplicationBAckend.Service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v2")
@RestController
@CrossOrigin
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private static final Logger log= LoggerFactory.getLogger(PostController.class);

    @PostMapping("/create/post")
    public ResponseEntity<Result> createPost(@RequestBody PostDTO postDTO){
        Result post = postService.createPost(postDTO);
        log.debug("calling the createPost getting {}",post);
        return new ResponseEntity<Result>(post, HttpStatus.OK);
    }

    @GetMapping("/get/post")
    public ResponseEntity<Result> getALlPosts(PageableDTO pageable){
        Result aLlPosts = postService.getALlPosts(pageable);
        return new ResponseEntity<>(aLlPosts,HttpStatus.OK);
    }

    @GetMapping("/get/Singlepost")
    public ResponseEntity<Result> getPostById(@RequestParam("id") int id){
        Result postById = postService.getPostById(id);
        return new ResponseEntity<>(postById,HttpStatus.OK);
    }

    @DeleteMapping("delete/post")
    public ResponseEntity<Result> deleteById(@RequestParam("id") int id){
        Result result = postService.deleteById(id);
        return new ResponseEntity<>(result,HttpStatus.CREATED);
    }

    @PutMapping("/upadte/post")
    public ResponseEntity<Result> updatePost(@RequestParam("id") int id,@RequestBody PostDTO postDTO){
        Result result = postService.updatePost(id, postDTO);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/post/all")
    public ResponseEntity<Result> getAllsPost(){
        Result result=postService.getAllsPost();
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
