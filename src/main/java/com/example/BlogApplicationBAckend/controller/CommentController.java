package com.example.BlogApplicationBAckend.controller;

import com.example.BlogApplicationBAckend.DTO.CommentDTO;
import com.example.BlogApplicationBAckend.DTO.PageableDTO;
import com.example.BlogApplicationBAckend.DTO.Result;
import com.example.BlogApplicationBAckend.Service.CommentService;
import com.example.BlogApplicationBAckend.ServiceImpl.CommentServiceimpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v2")
@RestController
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private static final Logger log= LoggerFactory.getLogger(CommentServiceimpl.class);

    @PostMapping("/create/comment")
    public ResponseEntity<Result> createComment(@RequestBody CommentDTO commentDTO){
        Result comment = commentService.createComment(commentDTO);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @GetMapping("/get/comment/all")
    public ResponseEntity<Result> getAllComment(PageableDTO pageableDTO){
        Result allComment = commentService.getAllComment(pageableDTO);
       return new ResponseEntity<>(allComment,HttpStatus.OK);
    }

    @GetMapping("/get/single/comment")
    public ResponseEntity<Result> getSingleComment(@RequestParam("id") int id){
        Result singleComment = commentService.getSingleComment(id);
        return new ResponseEntity<>(singleComment,HttpStatus.OK);
    }

    @DeleteMapping("/delete/comment")
    public ResponseEntity<Result> deleteSingleComment(@RequestParam("id") int id){
        Result result = commentService.deleteSingleComment(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PutMapping("/put/comment")
    public ResponseEntity<Result> updateComment(@RequestParam("id") int id,@RequestBody CommentDTO commentDTO){
        Result result = commentService.updateComment(id, commentDTO);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
