package com.rookie.ecommerce.service;

import com.rookie.ecommerce.entity.Product;
import com.rookie.ecommerce.repository.ProductRespository;
import com.rookie.ecommerce.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductServiceTest {
    @Mock
    private ProductRespository productRespository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void getProductsTest(){
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1L,"Product 1", "description",
                1.5, null, java.time.LocalDate.now(), java.time.LocalDate.now(),
                null,"ENABLE",5.0 ));
        productList.add(new Product(2L,"Product 2", "description",
                1.5, null, java.time.LocalDate.now(), java.time.LocalDate.now(),
                null,"ENABLE",5.0 ));
        productList.add(new Product(3L,"Product 3", "description",
                1.5, null, java.time.LocalDate.now(), java.time.LocalDate.now(),
                null,"ENABLE",5.0 ));
        when(productRespository.findAll()).thenReturn(productList);

        List<Product> products = productService.getProducts();

        assertEquals(3, products.size());

    }

    @Test
    public void getProductByStatusTest(){
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1L,"Product 1", "description",
                1.5, null, java.time.LocalDate.now(), java.time.LocalDate.now(),
                null,"ENABLE",5.0 ));
        productList.add(new Product(2L,"Product 2", "description",
                1.5, null, java.time.LocalDate.now(), java.time.LocalDate.now(),
                null,"ENABLE",5.0 ));
        productList.add(new Product(3L,"Product 3", "description",
                1.5, null, java.time.LocalDate.now(), java.time.LocalDate.now(),
                null,"ENABLE",5.0 ));
        when(productRespository.findByStatus("ENABLE")).thenReturn(productList);

        List<Product> p = productService.getProductsByStatus("ENABLE");

        assertEquals(3, p.size());
    }

    @Test
    public void getProductByIDTest(){
        Product product = new Product(3L,"Product 3", "description",
                1.5, null, java.time.LocalDate.now(), java.time.LocalDate.now(),
                null,"ENABLE",5.0 );
        when(productRespository.findById(product.getId())).thenReturn(Optional.of(product));

        Optional<Product> p = productService.getProductByID(product.getId());

        assertEquals(3L, p.get().getId());
    }

    @Test
    public void addProductTest(){
        Product product = new Product(3L,"Product 3", "description",
                1.5, null, java.time.LocalDate.now(), java.time.LocalDate.now(),
                null,"ENABLE",5.0 );
        when(productRespository.save(product)).thenReturn(product);

        Product p = productService.addProduct(product);

        assertEquals(3L, p.getId());
    }
}
