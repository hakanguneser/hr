package com.avinty.hr.model.DTO;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.avinty.hr.model.entity.DepartmentEntity} entity
 */
@Data
public class DepartmentDTO implements Serializable {
    private final Integer id;
    private final String name;
    private final EmployeeDTO manager;
}