package com.avinty.hr.service;

import com.avinty.hr.model.entity.EmployeeEntity;
import com.avinty.hr.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<EmployeeEntity> findAll() {
        return employeeRepository.findAll();
    }
}
