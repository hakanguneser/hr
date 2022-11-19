package com.avinty.hr.service;

import com.avinty.hr.model.entity.DepartmentEntity;
import com.avinty.hr.model.entity.EmployeeEntity;
import com.avinty.hr.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;

class EmployeeServiceTest {

    private EmployeeService underTest;
    @Mock
    private EmployeeRepository employeeRepository;

    DepartmentEntity DepartmentSlytherin, departmentGryffindor;
    EmployeeEntity employee1, employee2, employee3;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        underTest = new EmployeeService(employeeRepository);

        DepartmentSlytherin = DepartmentEntity.builder().name("Slytherin").build();
        departmentGryffindor = DepartmentEntity.builder().name("Gryffindor").build();
        employee1 = EmployeeEntity.builder()
                .email("severus@hogwarts.com")
                .password("11")
                .fullName("Severus Snape")
                .department(DepartmentSlytherin)
                .build();
        employee2 = EmployeeEntity.builder()
                .email("harry@hogwarts.com")
                .password("22")
                .fullName("Harry Potter")
                .department(departmentGryffindor)
                .build();
        employee3 = EmployeeEntity.builder()
                .email("tom@hogwarts.com")
                .password("33")
                .fullName("Tom Riddle")
                .department(DepartmentSlytherin)
                .build();

    }

    @Test
    void itShouldFindAll() {
        //Given saved employees
        List<EmployeeEntity> employeeList = Arrays.asList(employee1, employee2, employee3);
        given(employeeRepository.findAll()).willReturn(employeeList);
        //When
        List<EmployeeEntity> allReturnedEmployeeList = underTest.findAll();
        //Then
        Assertions.assertThat(allReturnedEmployeeList)
                .isNotNull()
                .hasSize(3)
                .contains(employee1, employee2, employee3);
    }
}