package com.smartest.store.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.smartest.store.controller.form.ProductForm;
import com.smartest.store.model.Product;
import com.smartest.store.repository.ProductRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * Controller responsible for handling product information
 * 
 * @author Tiago Santos
 * @since   2020-08-21
 * 
 */

@Api(value="Products", tags = {"Operations to handle products"})
@RestController
@RequestMapping(value = "/products")
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	
	/**
	   * This is  method returns all products from the database
	   * 
	   * @return list with all products
	   * @exception Exception on general errors.
	   * @see Products
	   */
	@ApiOperation(value = "Return a list with all products of the database", response = Product.class)
	@ApiResponses({
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Product> findAll() {
		
		logger.info("Start");
		
		Iterable<Product> findAll = productRepository.findAll();
		logger.info("All products?  -> "+findAll);

		logger.info("End");	
		return (List<Product>) findAll;
	}
	
	/**
	   * This is  method find a specific product by id
	   * 
	   * @return product 
	   * @exception Exception on general errors and not found.
	   * @see Product
	   */
	@ApiOperation(value = "Find and return product by id", response = Product.class)
	@ApiResponses({
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Product not found")})
	@GetMapping("/{id}")
	public ResponseEntity<Product> findByProductId(@PathVariable Integer id) {
		
		logger.info("Start");
		
		Optional<Product> product = productRepository.findById(id);
		logger.info("products?  -> "+product);
		
		ResponseEntity<Product> response = null;
		if (product.isPresent()) 
			response = ResponseEntity.ok(product.get());
		else
			response = ResponseEntity.notFound().build();
		
		logger.info("End");	
		return response;
	}
	
	/**
	   * This method is responsible for updating product by id
	   * 
	   * @param id identification of the product that needs to be updated.
	   * @param productForm product data that needs to be updated
	   * @return Product updated if no exception is thrown.
	   * @exception Exception on general errors.
	   * @see Product
	   */
	@ApiOperation(value = "Update product by id")
	@ApiResponses({
		@ApiResponse(code = 500, message = "Internal Server Error"),
		@ApiResponse(code = 404, message = "Product not found")})
	@PutMapping("/{id}")
	@Transactional 
	public ResponseEntity<Product> update(@PathVariable Integer id, @ApiParam(value = "productForm") @Valid @RequestBody ProductForm productForm, UriComponentsBuilder uriBuilder) {
		
		logger.info("Start");
		
		Optional<Product> optional = productRepository.findById(id);
		ResponseEntity<Product> response = null;
		if (optional.isPresent()) {
			Product productToUpdate = productForm.update(id, productRepository);
			response = ResponseEntity.ok(productToUpdate);
			logger.info("ProductToUpdate -> " + productToUpdate);
		} else {
			response = ResponseEntity.notFound().build();
			logger.info("Product not found. ProductID -> " + id);
		}
		
		logger.info("End");
		return response;
		
	}
	

	/**
	   * This is method is responsible for deleting products from the database
	   * 
	   * @param idProduct an integer that represents the product id.
	   * @return ResponseEntity code.
	   * @exception Exception on general errors.
	   */
	@DeleteMapping("/{idProduct}")
	@ApiOperation(value = "Delete product by id")
	@ApiResponses({
		@ApiResponse(code = 500, message = "Internal Server Error"),
		@ApiResponse(code = 404, message = "Product not found")})
	@Transactional
	public ResponseEntity<?> remove(@PathVariable("idProduct") Integer idProduct) {
		logger.info("Start");
		
		Optional<Product> optional = productRepository.findById(idProduct);
		ResponseEntity<?> response = null;
		
		if (optional.isPresent()) {
			productRepository.deleteById(idProduct);
			response = ResponseEntity.ok().build();
			logger.info("ProductDeleted  -> "+optional);
		}else {
			response = ResponseEntity.notFound().build();
			logger.info("ProductId "+idProduct+" not found");
		}
		
		logger.info("End");
		return response;
	}
	
	
}
