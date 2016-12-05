package com.checkout.kata.model;

import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpecialPrice implements Serializable{

    private String item;
    private int quantity;
    private int discountPrice;
}
