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
        User user = null;
        for (User u : users) {
            if (u.getId() == userId) {
                user = u;
                break;
            }
        }
        if (user == null) {
            return -1; // User not found
        }

        Merchant merchant = null;
        for (Merchant m : merchantService.getMerchants()) {
            if (m.getId() == merchantId) {
                merchant = m;
                break;
            }
        }
        if (merchant == null) {
            return -2; // Merchant not found
        }

        Product product = null;
        for (Product p : productService.getProducts()) {
            if (p.getId() == productId) {
                product = p;
                break;
            }
        }
        if (product == null) {
            return -3; // Product not found
        }

        System.out.println("Checking stock for merchantId: " + merchantId + ", productId: " + productId);
        boolean inStock = merchantStockService.checkStock(merchantId, productId);
        System.out.println("Stock available: " + inStock);

        if (!inStock) {
            return -4; // Product not in stock
        }

        if (user.getBalance() < product.getPrice()) {
            return -5; // Insufficient balance
        }

        boolean stockReduced = merchantStockService.reduceStock(merchantId, productId, 1);
        if (!stockReduced) {
            return -6; // Stock reduction failed
        }

        user.setBalance(user.getBalance() - product.getPrice()); // Deduct price from user balance
        return 0; // Purchase successful
    }

    }







