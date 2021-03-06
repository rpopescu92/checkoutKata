package com.checkout.kata.service;

import com.checkout.kata.model.Product;
import com.checkout.kata.model.SpecialPrice;
import com.checkout.kata.util.CheckoutRequest;
import com.checkout.kata.util.NoProductsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class CheckoutService {


    public double getTotalWithDiscount(CheckoutRequest checkoutRequest) {
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
            float priceWithoutDiscount = getProductStream(products, specialPrice)
                    .findFirst().get().getPrice();

            long countOccurrences = getProductStream(products, specialPrice)
                    .count();
            int multiplyDiscount = (int) countOccurrences / specialPrice.getQuantity();
            float totalPriceForCurrentProduct = priceWithoutDiscount * specialPrice.getQuantity();

            totalDiscount += multiplyDiscount * ( totalPriceForCurrentProduct - specialPrice.getDiscountPrice());
        }

        return totalDiscount;
    }

    private Stream<Product> getProductStream(List<Product> products, SpecialPrice specialPrice) {
        return products.stream()
                .filter(product -> product.getItem().equals(specialPrice.getItem()));
    }

    private double getTotalWithoutDiscount(List<Product> products) {
        return products.stream()
                .map(product -> product.getPrice())
                .mapToDouble(Float::floatValue).sum();
    }
}
