package com.avinty.hr.service;

import com.avinty.hr.exception.EntityNotFoundException;
import com.avinty.hr.mapper.EmployeeMapper;
import com.avinty.hr.model.DTO.EmployeeDTO;
import com.avinty.hr.model.entity.DepartmentEntity;
import com.avinty.hr.model.entity.EmployeeEntity;
import com.avinty.hr.model.request.EmployeeRequest;
import com.avinty.hr.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    @Lazy
    private final DepartmentService departmentService;

    private EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);

    public List<EmployeeDTO> findAll() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::EntityToDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO findById(Integer employeeId) {
        return employeeMapper.EntityToDTO(findEntityById(employeeId));
    }

    public EmployeeEntity findEntityById(Integer employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> {
            throw new EntityNotFoundException(String.format("Employee Not Found employeeId : %s", employeeId));
        });
    }

    public EmployeeDTO save(EmployeeRequest request) {
        EmployeeEntity employeeEntity = employeeMapper.RequestToEntity(request);

        if (Optional.ofNullable(request.getDepartmentId()).isPresent()) {
            DepartmentEntity departmentEntity = departmentService.findEntityById(request.getDepartmentId());
            employeeEntity.setDepartment(departmentEntity);
        }

        EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);
        return employeeMapper.EntityToDTO(savedEmployee);
    }

    public List<EmployeeDTO> findAllEmployeesInSameDepartment(Integer departmentId) {
        return employeeRepository.findByDepartmentId(departmentId).stream()
                .map(employeeMapper::EntityToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateEmployeesDepartment(Integer oldDepartmentId, Integer newDepartmentId) {
        employeeRepository.updateEmployeesDepartment(oldDepartmentId, newDepartmentId);
    }
}
