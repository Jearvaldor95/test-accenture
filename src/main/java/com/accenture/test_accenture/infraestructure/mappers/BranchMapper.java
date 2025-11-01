package com.accenture.test_accenture.infraestructure.mappers;

import com.accenture.test_accenture.domain.Branch;
import com.accenture.test_accenture.infraestructure.persistence.entities.BranchEntity;
import com.accenture.test_accenture.infraestructure.web.dtos.BranchResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    BranchEntity toEntity(Branch branch);

    Branch toDomain(BranchEntity branchEntity);

    BranchResponse toResponse(Branch branch);
}
