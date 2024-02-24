package com.example.order.service;

import com.example.order.dtos.ProductDTO;
import com.example.order.entities.Category;
import com.example.order.entities.Product;
import com.example.order.exceptions.EntityNotFoundException;
import com.example.order.repository.CategoryRepository;
import com.example.order.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ProductDTO createProduct(ProductDTO productDTO) throws EntityNotFoundException {
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setProductPrice(productDTO.getProductPrice());
        product.setProductObservations(productDTO.getProductObservations());
        product.setProductQuantity(productDTO.getProductQuantity());
        Category productCategory = categoryRepository.findById(productDTO.getProductCategory())
                .orElseThrow(() -> new EntityNotFoundException("Category id not found in request"));

        product.setProductCategory(productCategory);

        Product persistedProduct = productRepository.save(product);

        return new ProductDTO(persistedProduct.getProductId(),persistedProduct.getProductName(),
                persistedProduct.getProductObservations(),persistedProduct.getProductPrice(),
                persistedProduct.getProductQuantity(), persistedProduct.getProductCategory().getCategoryId());
    }

    public ProductDTO deleteProduct(Integer id) throws EntityNotFoundException {
        Optional<Product> productOpt = productRepository.findById(id);
        if(productOpt.isPresent()) {
            Product product = productOpt.get();
            productRepository.delete(product);
            return new ProductDTO(product.getProductId(),product.getProductName(), product.getProductObservations(),
                    product.getProductPrice(),product.getProductQuantity(),product.getProductCategory().getCategoryId());
        }else{
            throw new EntityNotFoundException(String.format("No product matching id %d was found",id
            ));
        }
    }

    public List<ProductDTO> getProducts(){
        return productRepository.findAll().stream().map(
                prod -> new ProductDTO(prod.getProductId(),prod.getProductName(),prod.getProductObservations(),
                        prod.getProductPrice(),prod.getProductQuantity(), prod.getProductCategory().getCategoryId())

        ).collect(Collectors.toList());
    }
}

