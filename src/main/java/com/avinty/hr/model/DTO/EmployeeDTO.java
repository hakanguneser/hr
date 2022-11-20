package com.avinty.hr.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.avinty.hr.model.entity.EmployeeEntity} entity
 */
@Data
public class EmployeeDTO implements Serializable {
    private final Integer id;
    private final String email;
    private final String password;
    private final String fullName;
    @JsonIgnore
    private final DepartmentDTO department;
}