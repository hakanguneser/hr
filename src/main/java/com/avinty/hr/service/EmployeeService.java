package com.avinty.hr.service;

import com.avinty.hr.mapper.EmployeeMapper;
import com.avinty.hr.model.DTO.EmployeeDTO;
import com.avinty.hr.model.entity.EmployeeEntity;
import com.avinty.hr.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);

    public List<EmployeeDTO> findAll() {
        return employeeRepository.findAll().stream().map(employeeMapper::EntityToDTO).collect(Collectors.toList());
    }
}
