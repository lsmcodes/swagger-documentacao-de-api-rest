package io.github.lsmcodes.swagger.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.lsmcodes.swagger.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

}