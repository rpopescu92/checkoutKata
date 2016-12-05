package com.checkout.kata.resource;

import com.checkout.kata.model.Product;
import com.checkout.kata.model.SpecialPrice;
import com.checkout.kata.service.CheckoutService;
import com.checkout.kata.util.CheckoutRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CheckoutResourceTest {

    @InjectMocks
    private CheckoutResource checkoutResource;

    @Mock
    private CheckoutService checkoutService;

    @Before
    public void setup() {
        standaloneSetup(checkoutResource).build();
    }

    @Test
    public void testWhenGetTotalPriceOk() {

        List<Product> products = new ArrayList<>();
        List<SpecialPrice> specialPrices = new ArrayList<>();
        CheckoutRequest checkoutRequest = CheckoutRequest.builder().products(products)
                                                .specialPrices(specialPrices).build();

        Mockito.when(checkoutService.getTotalWithDiscount(checkoutRequest)).thenReturn(3);
        ResponseEntity responseEntity = checkoutResource.getTotalPrice(checkoutRequest);
        Assert.assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
        Mockito.verify(checkoutService, times(1)).getTotalWithDiscount(checkoutRequest);
        Assert.assertTrue(responseEntity.getBody().equals(3));
    }

}
