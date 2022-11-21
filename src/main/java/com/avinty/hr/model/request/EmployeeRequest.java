package com.avinty.hr.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
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
    private Integer departmentId;
    private Boolean isActive;

}
