package com.shoppingcart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shoppingcart.model.Product; 

public interface ProductRepo extends JpaRepository<Product, Long> {	//Hereda métodos como findAll, findById, save,... que conectan con la BD

	@Query("SELECT COALESCE(MAX(p.price), 1000.0) FROM Product p")
	Double findMaxPrice();
	
	//Buscar por nombre y rango de precio
	List<Product> findByNameContainingIgnoreCaseAndPriceBetween(String name, double minPrice, double maxPrice);
	
	//Si no hay texto de búsqueda, busca por precio
	List<Product> findByPriceBetween(double minPrice, double maxPrice);
	
}