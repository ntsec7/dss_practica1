package com.shoppingcart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductSecurityTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	
	// /products/save se rechaza con CSRF inválido aunque seas admin
	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	void saveProduct_WithoutCsrf_ShoukdReturn403Forbidden() throws Exception {
		mockMvc.perform(post("/products/save")
				.param("name", "Albóndigas")
				.param("price", "5.80"))
				.andExpect(status().isForbidden());
	}
	
	
	// /products/save se acepta con CSRF válido siendo admin
	@Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void saveProduct_WithCsrf_ShouldRedirect() throws Exception {
        mockMvc.perform(post("/products/save")
        		.param("name", "Albóndigas")
				.param("price", "5.80")
                .with(csrf())) // pone un scrf válido
                .andExpect(status().is3xxRedirection()); // 302 Found
    }
	
	
	// /products/delete/{id} se rechaza con CSRF inválido aunque seas admin
		@Test
		@WithMockUser(username = "admin", roles = {"ADMIN"})
		void deleteProduct_WithoutCsrf_ShoukdReturn403Forbidden() throws Exception {
			mockMvc.perform(post("/products/delete/1"))
					.andExpect(status().isForbidden());
		}
		
		
		// /products/delete/{id} se acepta con CSRF válido siendo admin
		@Test
	    @WithMockUser(username = "admin", roles = {"ADMIN"})
	    void deleteProduct_WithCsrf_ShouldRedirect() throws Exception {
	        mockMvc.perform(post("/products/delete/1")
	                .with(csrf())) // pone un scrf válido
	                .andExpect(status().is3xxRedirection()); // 302 Found
	    }
	
	
	
}