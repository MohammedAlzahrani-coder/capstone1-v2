package com.example.capstone1.Controller;


import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.User;
import com.example.capstone1.Service.UserService;
import jakarta.servlet.MultipartConfigElement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;


    @GetMapping("/get")
    public ResponseEntity getUsers(){
        if(userService.getUsers().isEmpty()){
            return ResponseEntity.status(404).body(new ApiResponse("There is no any users"));
        }

        return ResponseEntity.status(200).body(userService.getUsers());
    }



    @PostMapping("/add")
    public ResponseEntity addUsers(@Valid @RequestBody User user, Errors errors){

        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        userService.addUser(user);
        return ResponseEntity.status(201).body(new ApiResponse("The user has been added successfully"));

    }



    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable int id, @Valid @RequestBody User user, Errors errors){

        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(201).body(message);
        }

        boolean isUpdated = userService.updateUser(id, user);
        if(isUpdated){

          return   ResponseEntity.status(201).body(new ApiResponse("The user has been updated succesfully"));
        }


        return ResponseEntity.status(404).body(new ApiResponse("The user is not found"));
    }


     @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable int id){

        boolean isDeleted = userService.deleteUser(id);

        if(isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("The user has been deleted successfully"));


        }

        return ResponseEntity.status(404).body(new ApiResponse("There is not user with this id"));

     }

    @PostMapping("/purchase")
    public ResponseEntity<ApiResponse> purchaseProduct(@RequestParam int userId, @RequestParam int productId, @RequestParam int merchantId) {
        int result = userService.purchase(userId, merchantId, productId);
        return switch (result) {
            case -1 -> ResponseEntity.badRequest().body(new ApiResponse("User not found"));
            case -2 -> ResponseEntity.badRequest().body(new ApiResponse("Merchant not found"));
            case -3 -> ResponseEntity.badRequest().body(new ApiResponse("Product not found"));
            case -4 -> ResponseEntity.badRequest().body(new ApiResponse("Product not in stock"));
            case -5 -> ResponseEntity.badRequest().body(new ApiResponse("Insufficient balance"));
            case -6 -> ResponseEntity.badRequest().body(new ApiResponse("Stock reduction failed"));
            default -> ResponseEntity.ok(new ApiResponse("Purchase successful"));
        };
    }

    }





