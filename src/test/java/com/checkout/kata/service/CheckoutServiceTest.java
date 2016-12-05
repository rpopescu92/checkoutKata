package com.checkout.kata.service;


import com.checkout.kata.model.Product;
import com.checkout.kata.model.SpecialPrice;
import com.checkout.kata.util.CheckoutRequest;
import com.checkout.kata.util.NoProductsException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CheckoutServiceTest {

    @InjectMocks
    private CheckoutService checkoutService;

    @Before
    public void setup(){
        standaloneSetup(checkoutService).build();
    }


    @Test
    public void testTotalWithDiscountWhenNoDiscounts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("A",34));
        products.add(new Product("B",50));

        List<SpecialPrice> specialPrices = new ArrayList<>();
        CheckoutRequest checkoutRequest = CheckoutRequest.builder()
                                                .products(products)
                                                .specialPrices(specialPrices)
                                                .build();
        int totalAmountToPay = checkoutService.getTotalWithDiscount(checkoutRequest);
        Assert.assertTrue(totalAmountToPay == 84);
    }

    @Test
    public void testTotalWithDiscountWhenDiscounts(){
        List<Product> products = new ArrayList<>();
        products.add(new Product("A",3));
        products.add(new Product("B",5));
        products.add(new Product("A",3));
        products.add(new Product("A",3));


        List<SpecialPrice> specialPrices = new ArrayList<>();
        specialPrices.add(new SpecialPrice("A",2,4));
        CheckoutRequest checkoutRequest = CheckoutRequest.builder()
                .products(products)
                .specialPrices(specialPrices)
                .build();
        int totalAmountToPay = checkoutService.getTotalWithDiscount(checkoutRequest);
        Assert.assertTrue(totalAmountToPay == 12);
    }

    @Test(expected = NoProductsException.class)
    public void testTotalWithDiscountWhenNoProducts() {
        List<Product> products = new ArrayList<>();
        List<SpecialPrice> specialPrices = new ArrayList<>();
        CheckoutRequest checkoutRequest = CheckoutRequest.builder()
                .products(products)
                .specialPrices(specialPrices)
                .build();
        int totalAmountToPay = checkoutService.getTotalWithDiscount(checkoutRequest);
    }
}
