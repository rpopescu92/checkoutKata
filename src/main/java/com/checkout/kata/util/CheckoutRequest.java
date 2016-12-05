package com.checkout.kata.util;

import com.checkout.kata.model.Product;
import com.checkout.kata.model.SpecialPrice;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutRequest implements Serializable {
    @JsonProperty("products")
    private List<Product> products;

    @JsonProperty("specialPrices")
    private List<SpecialPrice> specialPrices;
}
