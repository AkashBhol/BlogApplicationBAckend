package com.example.BlogApplicationBAckend.controller;

import com.example.BlogApplicationBAckend.DTO.CategoryDTO;
import com.example.BlogApplicationBAckend.DTO.PageableDTO;
import com.example.BlogApplicationBAckend.DTO.Result;
import com.example.BlogApplicationBAckend.Service.categoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v2")
@RestController
@CrossOrigin
public class CategoryController {
    @Autowired
    private categoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create/category")
    public ResponseEntity<Result> createCategory(@RequestBody CategoryDTO categoryDTO){
        Result category = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<Result>(category, HttpStatus.CREATED);
    }
    
    @PostMapping("/get/all/category")
    public ResponseEntity<Result> getAllCategory(PageableDTO pageable,@RequestBody CategoryDTO categoryDTO){
        Result allCategory = categoryService.getAllCategory(pageable,categoryDTO);
        return new ResponseEntity<Result>(allCategory,HttpStatus.OK);
    }
    
    @GetMapping("/get/single")
    public ResponseEntity<Result> getSingleCategory(@RequestParam("id") int id){
        Result singleCategory = categoryService.getSingleCategory(id);
        return new ResponseEntity<>(singleCategory,HttpStatus.OK);
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<Result> deleteCategory(@RequestParam("id") int id){
        Result result = categoryService.deleteCategory(id);
        return new ResponseEntity<Result>(result,HttpStatus.OK);
    }

    @PutMapping("/update/category")
    public  ResponseEntity<Result> updateCategory(@RequestParam("id") int id,@RequestBody  CategoryDTO categoryDTO){
        Result result = categoryService.updateCategory(id, categoryDTO);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/get/alls")
    public ResponseEntity<Result> getAlls(){
        Result alls = categoryService.getAlls();
        return new ResponseEntity<Result>(alls,HttpStatus.OK);
    }
}
