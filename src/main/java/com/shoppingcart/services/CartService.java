package com.shoppingcart.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shoppingcart.model.Product;
import com.shoppingcart.repository.ProductRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
	
	private List<Long> cart = new ArrayList<>();	//Guarda los ids de los productos
	private final ProductRepo productRepository;
	
	//Obtener artículos del carrito
	public List<Product> getShoppingCart(){
		List<Product> productsInCart = new ArrayList<>();
        List<Long> idsToRemove = new ArrayList<>();

        for (Long id : cart) {
            Optional<Product> productOpt = productRepository.findById(id);
            if (productOpt.isPresent()) {
                productsInCart.add(productOpt.get()); // Añade la versión actualizada (precio, nombre)
            } else {
                idsToRemove.add(id); // Si ya no existe en la BD, se marca para eliminar del carrito
            }
        }

        // Limpia de la sesión los IDs que fueron eliminados de la BD
        cart.removeAll(idsToRemove);

        return productsInCart;
	}
	
	//Añadir productos al carrito
	public void addProductToShoppingCart(Long productId){
		cart.add(productId);
	}
	
	//Eliminar productos del carrito
	public void deleteProductFromShoppingCart(Long productId) {
		cart.removeIf(id -> id.equals(productId));
	
	}
	
}
