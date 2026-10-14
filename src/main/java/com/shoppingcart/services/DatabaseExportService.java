package com.shoppingcart.services;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.shoppingcart.model.Product;
import com.shoppingcart.repository.ProductRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DatabaseExportService {
	
	private final ProductRepo productRepo;
	
    public byte[] exportDatabaseToSql() {
    	StringBuilder sql = new StringBuilder();

        sql.append("-- Exportación de base de datos de productos\n"); //Cabecera
        sql.append("SET NAMES 'utf8mb4';\n\n");	//índica que lo siguiente sigue formato UTF-8

        
        sql.append("DROP TABLE IF EXISTS product;\n");	//Sentencia para eliminar la tabla si ya existia
        sql.append("CREATE TABLE product (\n");	//Estructura de la tabla
        sql.append("    id BIGINT AUTO_INCREMENT PRIMARY KEY,\n");
        sql.append("    name VARCHAR(255) NOT NULL,\n");
        sql.append("    price DOUBLE NOT NULL\n");
        sql.append(");\n\n");

        
        List<Product> products = productRepo.findAll();

        if (!products.isEmpty()) {
            sql.append("-- Datos de productos\n");
            for (Product product : products) {
                // Escapar comillas simples por seguridad en SQL
                String safeName = (product.getName() != null) 
                        ? product.getName().replace("'", "''") 
                        : "";

                // Locale.US hace que se use '.' como separador decimal y así no de errores
                sql.append(String.format(
                    Locale.US,
                    "INSERT INTO product (id, name, price) VALUES (%d, '%s', %.2f);\n",
                    product.getId(),
                    safeName,
                    product.getPrice()
                ));
            }
        }

        //salida en UTF-8
        return sql.toString().getBytes(StandardCharsets.UTF_8);
    }
    
}
