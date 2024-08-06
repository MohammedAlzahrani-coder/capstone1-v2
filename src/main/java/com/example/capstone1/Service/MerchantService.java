package com.example.capstone1.Service;

import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MerchantService {



    ArrayList<Merchant> merchants = new ArrayList<>();

    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();

    private final MerchantStockService merchantStockService;



    public ArrayList<Merchant> getMerchants(){

        return merchants;

    }

    public void addMerchants(Merchant merchant){

        merchants.add(merchant);

    }

    public boolean updateMerchant(int id, Merchant merchant){

        for (int i = 0; i < merchants.size() ; i++) {
            if(merchants.get(i).getId() == id){
                merchants.set(i, merchant);
                return true;
            }
        }

        return false;
    }


    public boolean deleteMerchant(int id){
        for (Merchant merchant: merchants){

            if(merchant.getId() == id){

                merchants.remove(merchant);

                return true;
            }
        }

        return false;
    }




    public int getTotalSales(int merchantId) {
        int totalSales = 0;
        System.out.println("Calculating total sales for merchantId: " + merchantId);
        for (MerchantStock stock : merchantStockService.getMerchantStocks()) {
            System.out.println("Checking stock entry: " + stock);
            if (stock.getMerchantId() == merchantId) {
                totalSales += stock.getSales(); // Changed from getStock() to getSales()
                System.out.println("Added sales: " + stock.getSales() + ", Total sales: " + totalSales);
            }
        }
        System.out.println("Total sales for merchantId " + merchantId + ": " + totalSales);
        return totalSales;
    }

}
