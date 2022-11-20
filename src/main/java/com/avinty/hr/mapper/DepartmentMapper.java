package com.avinty.hr.mapper;

import com.avinty.hr.model.DTO.DepartmentDTO;
import com.avinty.hr.model.DTO.EmployeeDTO;
import com.avinty.hr.model.entity.DepartmentEntity;
import com.avinty.hr.model.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface DepartmentMapper {
    @Mappings({
            @Mapping(target = "manager.department.manager",ignore = true)
    })
    DepartmentEntity DTOToEntity(DepartmentDTO departmentDTO);

    @Mappings({
            @Mapping(target = "manager.department.manager",ignore = true)
    })
    DepartmentDTO EntityToDTO(DepartmentEntity departmentEntity);


}
