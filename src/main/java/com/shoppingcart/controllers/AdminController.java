package com.shoppingcart.controllers;



import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shoppingcart.services.DatabaseExportService;
import com.shoppingcart.services.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
	
	private final ProductService productService;
	private final DatabaseExportService databaseExportService;
	
	//Ir a la pantalla de administración (GET /admin)
	@GetMapping	//ruta raíz
	public String adminPage(Model model) {
		model.addAttribute("products", productService.getAllProducts()); //guarda todos los productos en model con la clave products
		return "admin"; 
	}
	
	//Exportar los datos de la bd (GET /admin/export)
	@GetMapping("/export")
	public ResponseEntity<byte[]> exportDB() {
		byte[] sqlBytes = databaseExportService.exportDatabaseToSql();
		
		//Configuración de archivos de descarga en el navegador
		HttpHeaders headers = new HttpHeaders();	//Objeto que almacena las cabeceras
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); //le dice al navegador que recibe datos binarios genéricos
		headers.setContentDispositionFormData("attachment", "shoppingcartdb.sql"); //attachment fuerza la descarga con nombre shoppingcartdb.sql
		headers.setContentLength(sqlBytes.length);	//Informa al navegador del tamaño del archivo en bytes
		
		return ResponseEntity.ok().headers(headers).body(sqlBytes);
	}

}
