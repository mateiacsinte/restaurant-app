package com.example.order.controller;

import com.example.order.dtos.ProductDTO;
import com.example.order.exceptions.EntityNotFoundException;
import com.example.order.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
       this.productService = productService;
    }

    @GetMapping("/products")
    public List<ProductDTO> getProducts(){
        return productService.getProducts();
    }

    @PostMapping("/products")
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) throws EntityNotFoundException {
        return productService.createProduct(productDTO);
    }

    @DeleteMapping("/products/{productId}")
    public ProductDTO deleteProduct(@PathVariable Integer productId) throws EntityNotFoundException{
        return productService.deleteProduct(productId);
    }
}
