package com.example.capstone1.Service;


import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {


    ArrayList<User> users = new ArrayList<>();

    private final MerchantService merchantService;

    private final MerchantStockService merchantStockService;

    private final ProductService productService;


    public ArrayList<User> getUsers(){

        return users;

    }

    public void addUser(User user){

        users.add(user);

    }

    public boolean updateUser(int id, User user){

        for (int i = 0; i < users.size() ; i++) {
            if(users.get(i).getId() == id){
                users.set(i, user);
                return true;
            }
        }

        return false;
    }


    public boolean deleteUser(int id){
        for (User user: users){

            if(user.getId() == id){

                users.remove(user);

                return true;
            }
        }

        return false;

    }



    public int purchase(int userId, int merchantId, int productId) {
        User user = users.stream()
                .filter(u -> u.getId() == userId)
                .findFirst()
                .orElse(null);
        if (user == null) {
            return -1; // User not found
        }

        Merchant merchant = merchantService.getMerchants().stream()
                .filter(m -> m.getId() == merchantId)
                .findFirst()
                .orElse(null);
        if (merchant == null) {
            return -2; // Merchant not found
        }

        Product product = productService.getProducts().stream()
                .filter(p -> p.getId() == productId)
                .findFirst()
                .orElse(null);
        if (product == null) {
            return -3; 
        }

        System.out.println("Checking stock for merchantId: " + merchantId + ", productId: " + productId);
        boolean inStock = merchantStockService.checkStock(merchantId, productId);
        System.out.println("Stock available: " + inStock);

        if (!inStock) {
            return -4; 
        }

        if (user.getBalance() < product.getPrice()) {
            return -5; 
        }

        boolean stockReduced = merchantStockService.reduceStock(merchantId, productId, 1);
        if (!stockReduced) {
            return -6; 
        }

        user.setBalance(user.getBalance() - product.getPrice()); 
        product.setSale(product.getSale() + 1); 
        product.setStocks(product.getStocks() - 1); 
        return 0; 
    }

    }







