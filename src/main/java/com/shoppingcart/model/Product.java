package com.shoppingcart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity  //Le dice a Spring Data JPA que esta clase es una tabla en la BD
@Data            // Genera getters, setters, toString, equals y hashCode
@NoArgsConstructor   // Genera el constructor vacío (requerido por JPA)
@AllArgsConstructor  // Genera el constructor con todos los atributos
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	private String name; 
	private double price; 
}
