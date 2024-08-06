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
        List<Product> topSellingProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getSale() > 0) { // Only consider products that have been purchased
                if (topSellingProducts.size() < 10) {
                    topSellingProducts.add(product);
                } else {
                    for (int i = 0; i < topSellingProducts.size(); i++) {
                        if (product.getSale() > topSellingProducts.get(i).getSale()) {
                            topSellingProducts.set(i, product);
                            break;
                        }
                    }
                }
            }
        }
        return topSellingProducts;
    }


    public List<Product> getLowStockProducts() {
        List<Product> lowStockProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getStocks() < 5) {
                lowStockProducts.add(product);
            }
        }
        return lowStockProducts;
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
