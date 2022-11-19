package com.avinty.hr.service;

import com.avinty.hr.mapper.EmployeeMapper;
import com.avinty.hr.model.DTO.EmployeeDTO;
import com.avinty.hr.model.entity.DepartmentEntity;
import com.avinty.hr.model.entity.EmployeeEntity;
import com.avinty.hr.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;

class EmployeeServiceTest {

    private EmployeeService underTest;
    private EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);
    @Mock
    private EmployeeRepository employeeRepository;

    DepartmentEntity departmentSlytherin, departmentGryffindor;
    EmployeeEntity employee1, employee2, employee3;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        underTest = new EmployeeService(employeeRepository);

        departmentSlytherin = DepartmentEntity.builder().id(1).name("Slytherin").build();
        departmentGryffindor = DepartmentEntity.builder().id(2).name("Gryffindor").build();
        employee1 = EmployeeEntity.builder()
                .id(1)
                .email("severus@hogwarts.com")
                .password("11")
                .fullName("Severus Snape")
                .department(departmentSlytherin)
                .build();
        employee2 = EmployeeEntity.builder()
                .id(2)
                .email("harry@hogwarts.com")
                .password("22")
                .fullName("Harry Potter")
                .department(departmentGryffindor)
                .build();
        employee3 = EmployeeEntity.builder()
                .id(3)
                .email("tom@hogwarts.com")
                .password("33")
                .fullName("Tom Riddle")
                .department(departmentSlytherin)
                .build();
        departmentGryffindor.setManager(employee2);
        departmentSlytherin.setManager(employee3);
    }

    @Test
    void itShouldFindAll() {
        //Given saved employees
        List<EmployeeEntity> employeeList = Arrays.asList(employee1, employee2, employee3);

        given(employeeRepository.findAll()).willReturn(employeeList);
        //When
        List<EmployeeDTO> allReturnedEmployeeList = underTest.findAll();
        //Then
        Assertions.assertThat(allReturnedEmployeeList)
                .isNotNull()
                .hasSize(3)
                .contains(employeeMapper.EntityToDTO(employee1),
                        employeeMapper.EntityToDTO(employee2),
                        employeeMapper.EntityToDTO(employee3));
    }
}