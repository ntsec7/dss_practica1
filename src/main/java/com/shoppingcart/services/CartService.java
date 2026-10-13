package com.shoppingcart.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.shoppingcart.model.Product;
import com.shoppingcart.repository.ProductRepo;

@Service
@SessionScope //para que cada usuario tenga su carrito
public class CartService {
	
	private List<Product> cart = new ArrayList<>();	//Guarda los ids de los productos
	//private final ProductRepo productRepository;
	
	//Obtener artículos del carrito
	//public List<Product> getShoppingCart(){
		//return cart;
	//}
	
	public List<Product> getShoppingCart(){
		for(Iterator it= productService.getProductById(id); it.hasNext()) {
			if(it.isPresent()) {
				
			}
		}
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
