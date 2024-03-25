package com.dslab.ecommercelab.service;

import com.dslab.ecommercelab.entity.Product;
import com.dslab.ecommercelab.entity.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class EcommProductService {

    @Autowired
    ProductRepository repository;
    public Product add(Product product) {
        return repository.save(product);
    }

    public Iterable<Product> getAll() {
        return repository.findAll();
    }

    public Optional<Product> getById(Integer productId) {
        return repository.findById(productId);
    }

    public String delete(Integer productId) {
        repository.deleteById(productId);
        return "Product with id: "+productId+" has been deleted!";
    }
}
