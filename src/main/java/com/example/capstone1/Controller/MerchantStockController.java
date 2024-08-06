package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Service.MerchantStockService;
import com.example.capstone1.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/merchantstock")
@RequiredArgsConstructor
public class MerchantStockController {




    private final MerchantStockService merchantStockService;




    @GetMapping("/get")
    public ResponseEntity getMerchantStock(){
        if(merchantStockService.getMerchantStocks().isEmpty()){
            return ResponseEntity.status(404).body(new ApiResponse("There is no any merchant stock"));
        }

        return ResponseEntity.status(200).body(merchantStockService.getMerchantStocks());
    }



    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@Valid @RequestBody MerchantStock merchantStock, Errors errors){

        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        merchantStockService.addMerchantStock(merchantStock);
        return ResponseEntity.status(201).body(new ApiResponse("The merchantstock has been added successfully"));

    }



    @PutMapping("/update/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable int id, @Valid @RequestBody MerchantStock merchantStock, Errors errors){

        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(201).body(message);
        }

        boolean isUpdated = merchantStockService.updateMerchantStock(id, merchantStock);
        if(isUpdated){

            return   ResponseEntity.status(201).body(new ApiResponse("The merchantstock has been updated succesfully"));
        }


        return ResponseEntity.status(404).body(new ApiResponse("The merchantstock is not found"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable int id){

        boolean isDeleted = merchantStockService.deleteMerchantStock(id);

        if(isDeleted){

            return ResponseEntity.status(200).body(new ApiResponse("The merchantstock has been deleted successfully"));

        }

        return ResponseEntity.status(404).body(new ApiResponse("There is no merchant stock with this id"));

    }


    @PutMapping("/moreStock/{merchantId}/{productId}/{additionalStocks}")
    public ResponseEntity addStocks(@PathVariable int merchantId,@PathVariable int productId, @PathVariable int additionalStocks){

        boolean isAdded = merchantStockService.addMoreStocks(merchantId,productId,additionalStocks);

        if(isAdded){
            return ResponseEntity.status(200).body(new ApiResponse("The new stocks has been added"));
        }

        return ResponseEntity.status(400).body(new ApiResponse("The merchant id or product id is wrong please check them out"));


    }


}
