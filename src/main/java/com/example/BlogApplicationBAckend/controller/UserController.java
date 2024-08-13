package com.example.BlogApplicationBAckend.controller;

import com.example.BlogApplicationBAckend.DTO.PageableDTO;
import com.example.BlogApplicationBAckend.DTO.PasswordDTO;
import com.example.BlogApplicationBAckend.DTO.Result;
import com.example.BlogApplicationBAckend.DTO.UserDTO;
import com.example.BlogApplicationBAckend.Entity.User;
import com.example.BlogApplicationBAckend.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v2")
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/user/create")
    public ResponseEntity<Result> createUser(@RequestBody UserDTO userDTO) {
        Result user = userService.createUser(userDTO);
        log.info("the message calling createUser in controller {}", user);
        return new ResponseEntity<Result>(user, HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    public ResponseEntity<Result> getAlluser(PageableDTO pageableDTO) {
        Result allUser = userService.getAllUser(pageableDTO);
        log.info("the message is calling the getAllUser in controller {}", allUser);
        return new ResponseEntity<>(allUser, HttpStatus.OK);
    }

    @GetMapping("/get/singele/user")
    public ResponseEntity<Result> getSingleUser(@RequestParam("id") int id) {
        Result singleUser = userService.getSingleUser(id);
        log.info("the message is calling the getSingleUser {}", singleUser);
        return new ResponseEntity<Result>(singleUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Result> deleteUser(@PathVariable int id) {
        Result result = userService.deleteUser(id);
        return new ResponseEntity<Result>(result, HttpStatus.OK);
    }

    @PutMapping("/update/user")
    public ResponseEntity<Result> updateUser(@RequestParam("id") int id, @RequestBody UserDTO userDTO) {
        Result result = userService.updateUser(id, userDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/update/password")
    public ResponseEntity<Result> updatePassword(@RequestBody PasswordDTO passwordDTO) {
        Result result = userService.updatePassword(passwordDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
