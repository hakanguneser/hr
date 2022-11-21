package com.avinty.hr.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    private String email;
    private String password;
    private String fullName;
    private Integer departmentId;
    private Boolean isActive;
}
