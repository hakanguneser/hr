package com.avinty.hr.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A DTO for the {@link com.avinty.hr.model.entity.EmployeeEntity} entity
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDTO implements Serializable {
    private Integer id;
    @Email
    @NotEmpty
    @Size(max = 255, message = "email has to be less than 255 characters")
    private String email;
    @NotEmpty
    @Size(min = 8,max = 66,message = "password has to be between min:8 max:66 characters")
    private String password;
    @NotEmpty
    @Size(min = 3,max = 200,message = "password has to be between min:3 max:200 characters")
    private String fullName;
    private Boolean isActive;
    private DepartmentDTO department;
}