package com.accenture.test_accenture.infraestructure.mappers;

import com.accenture.test_accenture.domain.Franchise;
import com.accenture.test_accenture.infraestructure.persistence.entities.FranchiseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FranchiseMapper {

    FranchiseEntity toEntity(Franchise franchise);

    Franchise toDomain(FranchiseEntity franchiseEntity);
}
