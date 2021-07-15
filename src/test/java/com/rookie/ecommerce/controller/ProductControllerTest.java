package com.rookie.ecommerce.controller;

import com.rookie.ecommerce.DTO.ProductDTO;
import com.rookie.ecommerce.entity.Product;
import com.rookie.ecommerce.restcontroller.ProductController;
import com.rookie.ecommerce.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private List<Product> productList;

    private Product product;

    @BeforeEach
    void setUp(){
        productList = new ArrayList<>();
        product = new Product(1L,"Product 1", "description",
                1.5, null, java.time.LocalDate.now(), java.time.LocalDate.now(),
                null,"ENABLE",5.0 );
        productList.add(product);
        productList.add(new Product(2L,"Product 2", "description",
                1.5, null, java.time.LocalDate.now(), java.time.LocalDate.now(),
                null,"ENABLE",5.0 ));
        productList.add(new Product(3L,"Product 3", "description",
                1.5, null, java.time.LocalDate.now(), java.time.LocalDate.now(),
                null,"ENABLE",5.0 ));
    }

    @Test
    public void getAllProductsTest() throws Exception {
        when(productService.getProducts()).thenReturn(productList);
        this.mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(productList.size())));
    }
}
