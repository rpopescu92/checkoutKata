package com.checkout.kata.resource;

import com.checkout.kata.service.CheckoutService;
import com.checkout.kata.util.CheckoutRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class CheckoutResource {

    @Autowired
    private CheckoutService checkoutService;

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTotalPrice(@RequestBody CheckoutRequest checkoutRequest) {

        int totalWithDiscount = checkoutService.getTotalWithDiscount(checkoutRequest);
        return new ResponseEntity(totalWithDiscount, HttpStatus.OK);


    }
}
