package com.example.capstone1.Service;

import com.example.capstone1.Model.MerchantStock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantStockService {

    private final ArrayList<MerchantStock> merchantStocks = new ArrayList<>();

    public ArrayList<MerchantStock> getMerchantStocks() {
        return new ArrayList<>(merchantStocks);
    }

    public void addMerchantStock(MerchantStock merchantStock) {
        merchantStocks.add(merchantStock);
    }

    public boolean updateMerchantStock(int id, MerchantStock merchantStock) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId() == id) {
                merchantStocks.set(i, merchantStock);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchantStock(int id) {
        return merchantStocks.removeIf(merchantStock -> merchantStock.getId() == id);
    }

    public boolean addMoreStocks(int merchantId, int productId, int additionalStock) {
        for (MerchantStock stocks : merchantStocks) {
            if (stocks.getMerchantId() == merchantId && stocks.getProductId() == productId) {
                stocks.setStock(stocks.getStock() + additionalStock);
                return true;
            }
        }
        return false;
    }

    public boolean reduceStock(int merchantId, int productId, int quantity) {
        for (MerchantStock stocks : merchantStocks) {
            if (stocks.getMerchantId() == merchantId && stocks.getProductId() == productId) {
                if (stocks.getStock() >= quantity) {
                    stocks.setStock(stocks.getStock() - quantity);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkStock(int merchantId, int productId) {
        System.out.println("Checking stock for merchantId: " + merchantId + ", productId: " + productId);
        for (MerchantStock stock : merchantStocks) {
            System.out.println("Checking stock entry: " + stock);
            if (stock.getMerchantId() == merchantId && stock.getProductId() == productId) {
                System.out.println("Stock found for product: " + productId);
                return stock.getStock() > 0;
            }
        }
        System.out.println("No stock found for product: " + productId);
        return false;
    }

//    public void updateStock(int merchantId, int productId, int quantity) {
//        for (MerchantStock stock : merchantStocks) {
//            if (stock.getMerchantId() == merchantId && stock.getProductId() == productId) {
//                stock.setStock(stock.getStock() + quantity);
//                return;
//            }
//        }
//        // If stock entry does not exist, create a new one
//        merchantStocks.add(new MerchantStock(0, productId, merchantId, quantity));
//    }


}