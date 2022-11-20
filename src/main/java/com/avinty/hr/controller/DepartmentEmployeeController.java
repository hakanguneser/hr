package com.avinty.hr.controller;

import com.avinty.hr.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dep-emp")
@RequiredArgsConstructor
public class DepartmentEmployeeController {

    private final DepartmentService departmentService;
    
}
