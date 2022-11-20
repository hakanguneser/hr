package com.avinty.hr.service;

import com.avinty.hr.mapper.DepartmentMapper;
import com.avinty.hr.mapper.EmployeeMapper;
import com.avinty.hr.model.DTO.DepartmentDTO;
import com.avinty.hr.model.DTO.EmployeeDTO;
import com.avinty.hr.model.entity.DepartmentEntity;
import com.avinty.hr.model.entity.EmployeeEntity;
import com.avinty.hr.model.request.EmployeeRequest;
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
    private final DepartmentService departmentService;

    private EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);
    private DepartmentMapper departmentMapper = Mappers.getMapper(DepartmentMapper.class);

    public List<EmployeeDTO> findAll() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::EntityToDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO save(EmployeeRequest request) {
        EmployeeEntity employeeEntity = employeeMapper.RequestToEntity(request);
        DepartmentEntity departmentEntity = departmentService.findEntityById(request.getDepartmentId());
        employeeEntity.setDepartment(departmentEntity);
        EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);
        return employeeMapper.EntityToDTO(savedEmployee);
    }


}
