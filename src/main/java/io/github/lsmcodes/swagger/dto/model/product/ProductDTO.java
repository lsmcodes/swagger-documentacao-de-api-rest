package io.github.lsmcodes.swagger.dto.model.product;

import java.util.UUID;

import org.modelmapper.ModelMapper;

import io.github.lsmcodes.swagger.model.Product;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

        private UUID id;

        @NotNull(message = "Name cannot be null")
        private String name;

        @NotNull(message = "Price cannot be null")
        @Pattern(regexp = "\\d+(\\.\\d{1,2})?", message = "The price must be in the format 0.00")
        private String price;

        @NotNull(message = "Quantity cannot be null")
        private Integer quantity;

        public Product convertDTOToEntity() {
                return new ModelMapper().map(this, Product.class);
        }

}