package com.avinty.hr.mapper;

import com.avinty.hr.model.DTO.EmployeeDTO;
import com.avinty.hr.model.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface EmployeeMapper {
    @Mappings({
            @Mapping(target = "department", ignore = true)
    })
    EmployeeEntity DTOToEntity(EmployeeDTO employeeDTO);

    @Mappings({
            @Mapping(target = "department", ignore = true)
    })
    EmployeeDTO EntityToDTO(EmployeeEntity employeeEntity);

}
