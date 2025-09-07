package com.quiz.Controller;

import com.quiz.Service.CategoryService;
import com.quiz.model.quiz.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //add category
    @PostMapping("/")
    public ResponseEntity<?> addCategory(@RequestBody Category category){
        Category category1 = this.categoryService.addCategory(category);
        return ResponseEntity.ok(category1);
    }

    //get Category
    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getCategory(@PathVariable("categoryId") Long categoryId){
        Category category = this.categoryService.getCategory(categoryId);
        return ResponseEntity.ok(category);
    }

    //get All Categories
    @GetMapping("/")
    public ResponseEntity<?> getCategories(){
        return ResponseEntity.ok(this.categoryService.getCategories());
    }

    //update categories
    @PutMapping("/")
    public Category updateCategory(@RequestBody Category category){
        return  this.categoryService.updateCategory(category);
    }

    //delete category
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") Long categoryId){
        this.categoryService.deleteCategory(categoryId);
    }

}
