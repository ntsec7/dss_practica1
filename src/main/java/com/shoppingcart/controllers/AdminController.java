package com.shoppingcart.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppingcart.services.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
	
	private final ProductService productService;
	
	//Ir a la pantalla de administración (GET /admin)
	@GetMapping	//ruta raíz
	public String adminPage(Model model) {
		model.addAttribute("products", productService.getAllProducts()); //guarda todos los productos en model con la clave products
		return "admin"; 
	}
	
	//Exportar los datos de la bd (GET /admin/export)
	@GetMapping("/export")
	public String exportDB(Model model) {
		return "redirect:/admin";
	}

}
