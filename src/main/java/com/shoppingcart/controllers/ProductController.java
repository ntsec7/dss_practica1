package com.shoppingcart.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;

import com.shoppingcart.model.Product;
import com.shoppingcart.services.ProductService;

import lombok.RequiredArgsConstructor;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")	//ruta raíz, así cualquier petición empieza así: http://localhost:8080/products
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;
	
	//Listar productos (GET /products)
	@GetMapping	//ruta raíz
	public String listProducts(Model model) {
		model.addAttribute("products", productService.getAllProducts()); //guarda todos los productos en model con la clave products
		return "products/productos";	//renderiza src/main/resources/templates/products/productos.html
	}
	
	//Formulario para crear productos (GET /products/add)
	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("product", new Product());
		return "products/formulario-producto";	//Busca en /templates/products/formulario-producto.html
	}
	
	//Formulario para editar productos (GET /products/edit/id)
	@GetMapping("/edit/{id}")
	public String editProduct(@PathVariable Long id, Model model) {	//PathVariable extrae el número de la url y lo asigna a id
		Optional<Product> product = productService.getProductById(id);
		if (product.isPresent()) {
			model.addAttribute("product", product.get());
			return "products/form";
		}
		return "redirect:/products"; //redirige a la lista de productos
	}
	
	//Guardar un producto nuevo o editado (POST /products/save)
	@PostMapping("/save")
	public String saveProduct(@ModelAttribute Product product) { //ModalAttribute coge los datos de la url
		productService.saveProduct(product);
		return "redirect:/products"; //redirige a la lista de productos
	}
	
	//Eliminar un producto (POST /products/delete/id) 
	@PostMapping("/delete/{id}")
	public String deleteProduct(@PathVariable Long id, Model model) {
		productService.deleteProduct(id);
		return "redirect:/products";
	}
}
