package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Service.MerchantService;
import com.example.capstone1.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {



    private final MerchantService merchantService;




    @GetMapping("/get")
    public ResponseEntity getMerchant(){

        if(merchantService.getMerchants().isEmpty()){
            return ResponseEntity.status(404).body(new ApiResponse("There is no any merchant "));
        }

        return ResponseEntity.status(200).body(merchantService.getMerchants());
    }



    @PostMapping("/add")
    public ResponseEntity addMerchant(@Valid @RequestBody Merchant merchant, Errors errors){

        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

       merchantService.addMerchants(merchant);
        return ResponseEntity.status(201).body(new ApiResponse("The merchant has been added successfully"));

    }



    @PutMapping("/update/{id}")
    public ResponseEntity deleteMerchant(@PathVariable int id, @Valid @RequestBody Merchant merchant, Errors errors){

        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(201).body(message);
        }

        boolean isUpdated = merchantService.updateMerchant(id, merchant);
        if(isUpdated){

            return   ResponseEntity.status(201).body(new ApiResponse("The merchant has been updated succesfully"));
        }


        return ResponseEntity.status(404).body(new ApiResponse("The merchant is not found"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable int id){

        boolean isDeleted = merchantService.deleteMerchant(id);

        if(isDeleted){

            return ResponseEntity.status(200).body(new ApiResponse("The merchant has been deleted successfully"));

        }

        return ResponseEntity.status(404).body(new ApiResponse("There is no merchant with this id"));

    }



    @GetMapping("/total-sales/{merchantId}")
    public ResponseEntity<Integer> getTotalSales(@PathVariable int merchantId) {
        int totalSales = merchantService.getTotalSales(merchantId);
        return ResponseEntity.ok(totalSales);
    }

}
