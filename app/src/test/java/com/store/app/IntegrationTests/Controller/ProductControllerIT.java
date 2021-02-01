package com.store.app.IntegrationTests.Controller;

import com.store.app.model.response.ProductResponseModel;
import com.store.app.model.response.UserResponseModel;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProductControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads(){
        ProductResponseModel result = this.restTemplate.getForObject("/products/dccdf70f-9ddc-4570-a21d-59dee6a350d0", ProductResponseModel.class);
        System.out.println(result);
    }
}
