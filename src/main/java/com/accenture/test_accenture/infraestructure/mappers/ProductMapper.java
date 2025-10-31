package com.accenture.test_accenture.infraestructure.mappers;

import com.accenture.test_accenture.domain.Product;
import com.accenture.test_accenture.infraestructure.persistence.entities.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductEntity toEntity(Product product);

    Product toDomain(ProductEntity productEntity);

}
