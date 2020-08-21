package com.smartest.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartest.store.model.Product;


/**
 * 
 * Repository responsible for managing product data on database
 * 
 * @author Tiago Santos
 * @since   2020-08-21
 * 
 */
//@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
