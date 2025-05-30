package com.luis.products.service.implementation;

import com.luis.products.exception.NotFoundException;
import com.luis.products.model.Product;
import com.luis.products.repository.ProductRepository;
import com.luis.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProduct(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product find = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found"));
        find.setName(product.getName());
        find.setPrice(product.getPrice());
        return productRepository.save(find);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found"));
        productRepository.deleteById(id);
    }
}
