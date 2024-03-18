package com.dslab.ecommercelab.controller;

import com.dslab.ecommercelab.entity.Product;
import com.dslab.ecommercelab.entity.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    ProductRepository repository;

    @PostMapping("/add")
    public @ResponseBody
    Product add(@RequestBody Product product) {
        return repository.save(product);
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Product> getAll() {
        return repository.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<Product> getProduct (@PathVariable Integer id) {
        return repository.findById(id);
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody String deleteProduct(@PathVariable Integer id) {
        repository.deleteById(id);
        return String.format("Product with id %d deleted.", id);
    }
}
