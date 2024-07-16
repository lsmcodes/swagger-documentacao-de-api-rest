package io.github.lsmcodes.swagger.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.lsmcodes.swagger.dto.model.product.ProductDTO;
import io.github.lsmcodes.swagger.dto.response.Response;
import io.github.lsmcodes.swagger.exception.ProductNotFoundException;
import io.github.lsmcodes.swagger.model.Product;
import io.github.lsmcodes.swagger.service.ProductService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
@OpenAPIDefinition(info = @Info(title = "Sample Product Management API"))
public class ProductController {

        @Autowired
        ProductService service;

        @GetMapping
        @Operation(summary = "Get all products")
        public ResponseEntity<Response<List<ProductDTO>>> getProducts() throws ProductNotFoundException {
                Response<List<ProductDTO>> response = new Response<>();

                List<Product> products = service.getProducts();

                if (products.isEmpty()) {
                        throw new ProductNotFoundException("There are no products registered yet");
                }

                List<ProductDTO> productDTOs = products.stream().map(product -> product.convertEntityToDTO()).toList();
                response.setData(productDTOs);

                return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        @PostMapping
        @Operation(summary = "Create a product")
        public ResponseEntity<Response<ProductDTO>> createProduct(@RequestBody @Valid ProductDTO dto,
                        BindingResult result) {
                Response<ProductDTO> response = new Response<>();

                if (result.hasErrors()) {
                        result.getAllErrors().stream()
                                        .forEach(error -> response.addErrorToMessage(400, error.getDefaultMessage()));

                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                }

                Product product = dto.convertDTOToEntity();
                Product savedProduct = this.service.saveProduct(product);

                ProductDTO savedProductDTO = savedProduct.convertEntityToDTO();
                response.setData(savedProductDTO);

                return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        @PutMapping("/{id}")
        @Operation(summary = "Update a product")
        public ResponseEntity<Response<ProductDTO>> updateProduct(@PathVariable UUID id,
                        @RequestBody @Valid ProductDTO dto,
                        BindingResult result) throws ProductNotFoundException {
                Response<ProductDTO> response = new Response<>();

                if (result.hasErrors()) {
                        result.getAllErrors().stream()
                                        .forEach(error -> response.addErrorToMessage(400, error.getDefaultMessage()));

                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                }

                Product foundProduct = this.service.getProduct(id);
                foundProduct.setName(dto.getName());
                foundProduct.setQuantity(dto.getQuantity());

                Product savedProduct = this.service.saveProduct(foundProduct);
                ProductDTO savedProductDTO = savedProduct.convertEntityToDTO();
                response.setData(savedProductDTO);

                return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        @DeleteMapping("/{id}")
        @Operation(summary = "Delete a product")
        public ResponseEntity<Response<String>> deleteProduct(@PathVariable UUID id) throws ProductNotFoundException {
                Response<String> response = new Response<>();

                Product foundProduct = this.service.getProduct(id);
                this.service.deleteProduct(foundProduct);

                response.setData("Trip with id " + foundProduct.getId() + " successfully deleted");

                return ResponseEntity.status(HttpStatus.OK).body(response);
        }

}