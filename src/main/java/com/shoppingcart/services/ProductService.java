package com.shoppingcart.services;

import com.shoppingcart.model.Product;
import com.shoppingcart.repository.ProductRepo;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
	
	private final ProductRepo productRepo;
	
	public List<Product> getAllProducts(){
		return productRepo.findAll();
	}
	
	public Optional<Product> getProductById(Long id) {	//Optional por si el producto no existe
		return productRepo.findById(id);
	}
	
	public Product saveProduct(Product product) {
		return productRepo.save(product);
	}
	
	public void deleteProduct(Long id) {
		productRepo.deleteById(id);
	}
	
	
}
