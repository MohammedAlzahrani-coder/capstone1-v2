package com.example.capstone1.Service;

import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductService {


    ArrayList<Product> products = new ArrayList<>();



    public ArrayList<Product> getProducts(){

        return products;

    }

    public void addProduct(Product product){

        products.add(product);

    }

    public boolean updateProduct(int id, Product product){

        for (int i = 0; i < products.size() ; i++) {
            if(products.get(i).getId() == id){
                products.set(i, product);
                return true;
            }
        }

        return false;
    }


    public boolean deleteProduct(int id){
        for (Product product: products){

            if(product.getId() == id){

                products.remove(product);

                return true;
            }
        }

        return false;
    }


    public List<Product> getTopSellingProducts() {
        return products.stream()
                .filter(product -> product.getSale() > 0)
                .sorted(Comparator.comparingInt(Product::getSale).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<Product> getLowStockProducts() {
        return products.stream()
                .filter(product -> product.getStocks() < 5)
                .collect(Collectors.toList());
    }


    public List<Product> getProductsByPriceRange(int minPrice, int maxPrice) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                result.add(product);
            }
        }
        return result;
    }


    public List<Product> getProductsByMinSales(int minSales) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getSale() >= minSales) {
                result.add(product);
            }
        }
        return result;
    }

}
