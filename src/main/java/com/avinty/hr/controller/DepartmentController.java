package com.avinty.hr.controller;

import com.avinty.hr.model.DTO.DepartmentDTO;
import com.avinty.hr.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/all")
    public ResponseEntity<List<DepartmentDTO>> findAll() {
        return ResponseEntity.ok(departmentService.findAll());
    }

    @GetMapping()
    public ResponseEntity<List<DepartmentDTO>> findAllByNameLike(@RequestParam("name") String name){
        return ResponseEntity.ok(departmentService.findAllByNameLike(name));
    }
    @PostMapping
    public ResponseEntity save(@RequestBody DepartmentDTO departmentDTO) {
        departmentService.save(departmentDTO);
        return ResponseEntity.ok().build();
    }

}
