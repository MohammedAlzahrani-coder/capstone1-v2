package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.Category;
import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Service.CategoryService;
import com.example.capstone1.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {


    private final CategoryService categoryService;




    @GetMapping("/get")
    public ResponseEntity getCategory(){

        if(categoryService.getCategories().isEmpty()){
            return ResponseEntity.status(404).body(new ApiResponse("There is no any category "));
        }

        return ResponseEntity.status(200).body(categoryService.getCategories());
    }



    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid @RequestBody Category category, Errors errors){

        if(errors.hasErrors()){

            String message = errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(400).body(message);

        }

        categoryService.addCategory(category);
        return ResponseEntity.status(201).body(new ApiResponse("The category has been added successfully"));


    }



    @PutMapping("/update/{id}")
    public ResponseEntity deleteCategory(@PathVariable int id, @Valid @RequestBody Category category, Errors errors){

        if(errors.hasErrors()){

            String message = errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(201).body(message);

        }

        boolean isUpdated = categoryService.updateCategory(id, category);
        if(isUpdated){

            return   ResponseEntity.status(201).body(new ApiResponse("The category has been updated succesfully"));

        }


        return ResponseEntity.status(404).body(new ApiResponse("The category is not found"));

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable int id){

        boolean isDeleted = categoryService.deleteCategory(id);

        if(isDeleted){

            return ResponseEntity.status(200).body(new ApiResponse("The category has been deleted successfully"));

        }

        return ResponseEntity.status(404).body(new ApiResponse("There is no category with this id"));

    }



}
