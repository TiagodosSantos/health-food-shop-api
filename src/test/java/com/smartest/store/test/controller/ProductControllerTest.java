package com.smartest.store.test.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.smartest.store.model.Product;
import com.smartest.store.repository.ProductRepository;



/**
 * 
 * Test ProductController using spring boot test
 * 
 * @author Tiago Santos
 * @since 2020-08-21
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductControllerTest.class);
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	@MockBean
    private ProductRepository productRepository;

	@Test
	public void testFindAllSuccess() throws URISyntaxException {
		
		logger.info("START TEST:: testFindAllSuccess");
		
		Product product = new Product("Product 1", "Product Test 1", 10d);
		Product product2 = new Product("Product 2", "Product Test 2", 20d);
		
		// given
        given(productRepository.findAll()).willReturn(Arrays.asList(product, product2));
		
        // when
        ResponseEntity<List> productResponse = restTemplate.getForEntity("/products", List.class);
        logger.info("TEST RESPONSE:: "+ productResponse);
		
        // then
		assertEquals(HttpStatus.OK.value(), productResponse.getStatusCodeValue());
		
		logger.info("END TEST:: testFindAllSuccess");
	}

	@Test
	public void testFindByProductIdSuccess() throws URISyntaxException {
		
		logger.info("START TEST:: testFindByProductIdSuccess");
		
		Product product = new Product("Product 1", "Product Test 1", 10d);
		
		// given
        given(productRepository.findById(1)).willReturn(Optional.of(product));
		
        // when
        ResponseEntity<Product> productResponse = restTemplate.getForEntity("/products/1", Product.class);
        logger.info("TEST RESPONSE:: "+ productResponse);
		
        // then
		assertEquals(HttpStatus.OK.value(), productResponse.getStatusCodeValue());
		
		logger.info("END TEST:: testFindByProductIdSuccess");
	}
	
}