package com.avinty.hr.service;

import com.avinty.hr.mapper.DepartmentMapper;
import com.avinty.hr.model.DTO.DepartmentDTO;
import com.avinty.hr.model.entity.DepartmentEntity;
import com.avinty.hr.model.entity.EmployeeEntity;
import com.avinty.hr.repository.DepartmentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class DepartmentServiceTest {

    private DepartmentService underTest;

    @Mock
    private DepartmentRepository departmentRepository;

    private DepartmentMapper departmentMapper = Mappers.getMapper(DepartmentMapper.class);

    DepartmentEntity departmentSlytherin, departmentGryffindor;
    EmployeeEntity employee1, employee2, employee3;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        underTest = new DepartmentService(departmentRepository);

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
        //Given
        List<DepartmentEntity> departmentList = Arrays.asList(departmentGryffindor,departmentSlytherin);
        given(departmentRepository.findAll()).willReturn(departmentList);
        //When
        List<DepartmentDTO> allReturnedDepartmentList = underTest.findAll();
        //Then
        Assertions.assertThat(allReturnedDepartmentList)
                .isNotNull()
                .hasSize(departmentList.size())
                .contains(departmentMapper.EntityToDTO(departmentGryffindor),
                          departmentMapper.EntityToDTO(departmentSlytherin));
    }
}