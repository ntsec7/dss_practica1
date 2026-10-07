package com.shoppingcart.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")	//ruta raíz
	public String redirectToProducts() {	//redirige a /products
		return "redirect:/products";
	}
}
