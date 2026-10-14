package com.shoppingcart.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppingcart.model.Product;
import com.shoppingcart.services.CartService;
import com.shoppingcart.services.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
	
	private final CartService cartService;
	private final ProductService productService;

	//Ver carrito(GET /cart)
	@GetMapping
	public String getCart(Model model) {
		model.addAttribute("cartItems", cartService.getShoppingCart());
		return "cart";
	}
	
	//Añadir al carrito(POST /cart/add/id)
	@PostMapping("/add/{id}")
	public String addToCart(@PathVariable Long id) {
		Optional<Product> product = productService.getProductById(id);
		if(product.isPresent()) {
			cartService.addProductToShoppingCart(id);
		} 
		return "redirect:/products";
	}
	
	//Eliminar del carrito(POST /cart/delete/id)
	@PostMapping("/remove/{id}")
	public String deleteFromCart(@PathVariable Long id) {
		Optional<Product> product = productService.getProductById(id);
		if(product.isPresent()) {
			cartService.deleteProductFromShoppingCart(id);
		}
		return "redirect:/cart";
	}
	
}
