package com.checkout.kata.service;

import com.checkout.kata.model.Product;
import com.checkout.kata.model.SpecialPrice;
import com.checkout.kata.util.CheckoutRequest;
import com.checkout.kata.util.NoProductsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckoutService {


    public int getTotalWithDiscount(CheckoutRequest checkoutRequest) {
        int totalDiscount = getTotalDiscount(checkoutRequest);

        return getTotalWithoutDiscount(checkoutRequest.getProducts()) - totalDiscount;
    }

    private int getTotalDiscount(CheckoutRequest checkoutRequest) {
        int totalDiscount = 0;
        List<Product> products = checkoutRequest.getProducts();
        List<SpecialPrice> specialPrices = checkoutRequest.getSpecialPrices();

        if(products.size() == 0){
            throw new NoProductsException();
        }

        for(SpecialPrice specialPrice: specialPrices)
        {
            int priceWithoutDiscount = products.stream()
                    .filter(product -> product.getItem().equals(specialPrice.getItem()))
                    .findFirst().get().getPrice();

            long countOccurrences = products.stream()
                    .filter(product -> product.getItem().equals(specialPrice.getItem()))
                    .count();
            int multiplyDiscount = (int) countOccurrences / specialPrice.getQuantity();
            int totalPriceForCurrentProduct = priceWithoutDiscount * specialPrice.getQuantity();

            totalDiscount += multiplyDiscount * ( totalPriceForCurrentProduct - specialPrice.getDiscountPrice());
        }

        return totalDiscount;
    }

    private int getTotalWithoutDiscount(List<Product> products) {
        return products.stream()
                .map(product -> product.getPrice())
                .mapToInt(Integer::intValue).sum();
    }
}
