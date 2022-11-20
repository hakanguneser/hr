package com.avinty.hr.controller;

import com.avinty.hr.model.DTO.DepartmentEmployeesDTO;
import com.avinty.hr.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dep-emp")
@CrossOrigin(origins = "http://localhost:5000", maxAge = 3600)
@RequiredArgsConstructor
public class DepartmentEmployeeController {

    private final DepartmentService departmentService;

    @GetMapping
    public List<DepartmentEmployeesDTO> getAllDepartmentWithEmployees(){
        return departmentService.findAllDepartmentWithEmployees();
    }
}
