package com.store.app.UnitTests.Controler.Product;

import com.store.app.controller.ProductController;
import com.store.app.database.repository.UserRepository;
import com.store.app.dto.ProductDto;
import com.store.app.service.CartService;
import com.store.app.service.ProductService;
import com.store.app.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private CartService cartService;

    // TODO Do not know why this is expected
    @MockBean
    private UserService userService;
    @MockBean
    private UserRepository userRepository;


    @Test
    public void getProduct()throws Exception{
        when(productService.get("publicProductId")).thenReturn(new ProductDto(1,"publicProductId","productName","productBrand",
                "productModel","productDescription",100,10));

        RequestBuilder request = MockMvcRequestBuilders.get("/products/publicProductId").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{publicProductId:publicProductId," +
                        "productName:productName," +
                        "productBrand:productBrand," +
                        "productModel:productModel," +
                        "productDescription:productDescription," +
                        "quantityOfStock:100," +
                        "productPrice:10}"))
                .andReturn();
    }
    @Test
    public void getProductList()throws Exception{
        List<ProductDto> list = new ArrayList<>();
        list.add(new ProductDto(1,"publicProductId1","productName1","productBrand1",
                        "productModel1","productDescription1",100,10));
        list.add(new ProductDto(2,"publicProductId2","productName2","productBrand2",
                "productModel2","productDescription2",55,33));

        when(productService.getList(0,10)).thenReturn(list);

        RequestBuilder request = MockMvcRequestBuilders.get("/products").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{publicProductId:publicProductId1," +
                        "productName:productName1," +
                        "productBrand:productBrand1," +
                        "productModel:productModel1," +
                        "productDescription:productDescription1," +
                        "quantityOfStock:100," +
                        "productPrice:10}," +
                        "{publicProductId:publicProductId2," +
                        "productName:productName2," +
                        "productBrand:productBrand2," +
                        "productModel:productModel2," +
                        "productDescription:productDescription2," +
                        "quantityOfStock:55," +
                        "productPrice:33}]"))
                .andReturn();
    }
}