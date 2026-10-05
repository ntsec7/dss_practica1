package com.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingcart.model.Product; 

public interface ProductRepo extends JpaRepository<Product, Long> {	//Hereda métodos como findAll, findById, save,... que conectan con la BD
}