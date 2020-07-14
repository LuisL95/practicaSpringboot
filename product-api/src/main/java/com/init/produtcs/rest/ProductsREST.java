package com.init.produtcs.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.init.produtcs.dao.ProductsDAO;
import com.init.produtcs.entities.Product;

@RestController	
@RequestMapping("products")
public class ProductsREST {

	
	/*
	//Ejemplo de otra forma de hacerlo
	//@RequestMapping(value="hello" , method = RequestMethod.GET)
	  //@GetMapping
	
	public String hello() {
		
		return "hello world";
	}
	*/
	@Autowired
	private ProductsDAO productDAO;
	
	
	
	
	@GetMapping
	public ResponseEntity<List <Product>> getProducts(){
		
		List <Product> products = productDAO.findAll();
		return ResponseEntity.ok(products);
		/*
		Product product1 = new Product();
		
		product1.setId(1L);
		product1.setName("producto 1");
		return ResponseEntity.ok(product1);
		
		
		return null;
		*/
		}
	
		@RequestMapping(value ="{productId}")  //products/{productId} ejemplo: products/1
		public ResponseEntity<Product> getProduc(@PathVariable("productId") Long productId)
		{
			
		Optional<Product> optionalProduct =	productDAO.findById(productId);
		
			if(optionalProduct.isPresent()) 
			{
				return ResponseEntity.ok(optionalProduct.get());
				
			}else
			{
				return ResponseEntity.noContent().build();
			}
		
		}
		
		@PostMapping
		public ResponseEntity<Product> createProduct(@RequestBody Product producto)
		{
			Product nuevoProducto = productDAO.save(producto);
			return ResponseEntity.ok(nuevoProducto);
		}
		
		@DeleteMapping(value="{productId}")
		public ResponseEntity<Void> eliminarProducto (@PathVariable("productId") Long productId)
		{
			productDAO.deleteById(productId);
			return ResponseEntity.ok(null);
		}
		
		@PutMapping
		public ResponseEntity<Product> modificarProducto(@RequestBody Product product)
		{
			Optional<Product> optionalProduct = productDAO.findById(product.getId());
			if(optionalProduct.isPresent())
			{
				Product productoModificado = optionalProduct.get();
				productoModificado.setName(product.getName());
				productDAO.save(productoModificado);
				return ResponseEntity.ok(productoModificado);
			}
			else
			{
				return ResponseEntity.notFound().build();
		
		}
		
		
}
