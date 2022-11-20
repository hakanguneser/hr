package com.avinty.hr.model.request;

import lombok.Data;

@Data
public class EmployeeRequest {
    private String email;
    private String password;
    private String fullName;
    private Integer departmentId;
}
