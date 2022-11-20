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
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

class DepartmentServiceTest {

    private DepartmentService underTest;

    @Mock
    private DepartmentRepository departmentRepository;

    private DepartmentMapper departmentMapper = Mappers.getMapper(DepartmentMapper.class);

    @Captor
    private ArgumentCaptor<DepartmentEntity> departmentEntityArgumentCaptor;
    DepartmentEntity departmentEntitySlytherin, departmentEntityGryffindor;
    EmployeeEntity employeeEntitySeverus, employeeEntityHarry, employeeEntityTom;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        underTest = new DepartmentService(departmentRepository);

        departmentEntitySlytherin = DepartmentEntity.builder().id(1).name("Slytherin").build();
        departmentEntityGryffindor = DepartmentEntity.builder().id(2).name("Gryffindor").build();

        employeeEntitySeverus = EmployeeEntity.builder()
                .id(1)
                .email("severus@hogwarts.com")
                .password("11")
                .fullName("Severus Snape")
                .department(departmentEntitySlytherin)
                .build();
        employeeEntityHarry = EmployeeEntity.builder()
                .id(2)
                .email("harry@hogwarts.com")
                .password("22")
                .fullName("Harry Potter")
                .department(departmentEntityGryffindor)
                .build();
        employeeEntityTom = EmployeeEntity.builder()
                .id(3)
                .email("tom@hogwarts.com")
                .password("33")
                .fullName("Tom Riddle")
                .department(departmentEntitySlytherin)
                .build();

        departmentEntityGryffindor.setManager(employeeEntityHarry);
        departmentEntitySlytherin.setManager(employeeEntityTom);
    }
    @Test
    void itShouldFindAll() {
        //Given
        List<DepartmentEntity> departmentList = Arrays.asList(departmentEntityGryffindor, departmentEntitySlytherin);
        given(departmentRepository.findAll()).willReturn(departmentList);
        //When
        List<DepartmentDTO> allReturnedDepartmentList = underTest.findAll();
        //Then
        Assertions.assertThat(allReturnedDepartmentList)
                .isNotNull()
                .hasSize(departmentList.size())
                .contains(departmentMapper.EntityToDTO(departmentEntityGryffindor),
                          departmentMapper.EntityToDTO(departmentEntitySlytherin));
    }

    @Test
    void itShouldSaveDepartment(){
        //Given
        DepartmentDTO departmentDTO = departmentMapper.EntityToDTO(departmentEntitySlytherin);
        //When
        underTest.save(departmentDTO);
        //Then
        then(departmentRepository).should().save(departmentEntityArgumentCaptor.capture());
        String departmentEntityCapturedValue = departmentEntityArgumentCaptor.getValue().getName();

        Assertions.assertThat(departmentEntityCapturedValue)
                .isNotNull()
                .isEqualTo(departmentDTO.getName());
    }
}