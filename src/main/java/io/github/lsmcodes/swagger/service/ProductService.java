package io.github.lsmcodes.swagger.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.lsmcodes.swagger.exception.ProductNotFoundException;
import io.github.lsmcodes.swagger.model.Product;
import io.github.lsmcodes.swagger.repository.ProductRepository;

@Service
public class ProductService {

        @Autowired
        ProductRepository repository;

        public Product getProduct(UUID id) throws ProductNotFoundException {
                return this.repository.findById(id).orElseThrow(
                                () -> new ProductNotFoundException("There is no product registered with the id " + id));
        }

        public List<Product> getProducts() {
                return this.repository.findAll();
        }

        public Product saveProduct(Product product) {
                return this.repository.save(product);
        }

        public void deleteProduct(Product product) {
                this.repository.delete(product);
        }

}