package com.avinty.hr.controller;

import com.avinty.hr.model.DTO.EmployeeDTO;
import com.avinty.hr.model.request.EmployeeRequest;
import com.avinty.hr.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> findAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> save(@RequestBody EmployeeRequest request) {
        EmployeeDTO savedEmployee = employeeService.save(request);
        return ResponseEntity.ok(savedEmployee);
    }

}
