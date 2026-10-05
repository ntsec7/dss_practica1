package com.shoppingcart.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.shoppingcart.model.Product;

@Service
@SessionScope //para que cada usuario tenga su carrito
public class CartService {
	
	private List<Product> cart = new ArrayList<>();
	
	//Obtener artículos del carrito
	public List<Product> getShoppingCart(){
		return cart;
	}
	
	//Añadir productos al carrito
	public void addProductToShoppingCart(Product product){
		cart.add(product);
	}
	
	//Eliminar productos del carrito
	public void deleteProductFromShoppingCart(Product product) {
		cart.remove(product);
	}
	
}
