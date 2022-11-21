package com.avinty.hr.model.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link com.avinty.hr.model.entity.DepartmentEntity} entity
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentDTO implements Serializable {
    private Integer id;
    @NotNull
    @Size(min = 3,max = 100,message = "department name has to be between min:3 max:100 characters")
    private String name;
    private EmployeeDTO manager;
}