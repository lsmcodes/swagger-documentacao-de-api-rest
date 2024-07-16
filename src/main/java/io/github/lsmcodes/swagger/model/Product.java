package io.github.lsmcodes.swagger.model;

import java.util.UUID;

import org.modelmapper.ModelMapper;

import io.github.lsmcodes.swagger.dto.model.product.ProductDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID id;

        @Column(nullable = false)
        private String name;

        @Column(nullable = false)
        private Double price;

        @Column(nullable = false)
        private Integer quantity;

        public ProductDTO convertEntityToDTO() {
                return new ModelMapper().map(this, ProductDTO.class);
        }

}