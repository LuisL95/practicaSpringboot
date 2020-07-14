package com.init.produtcs.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.init.produtcs.entities.Product;



public interface ProductsDAO extends JpaRepository<Product,Long> {

	
}
