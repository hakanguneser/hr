package com.avinty.hr.service;

import com.avinty.hr.exception.EntityNotFoundException;
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
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
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
        departmentEntityGryffindor.setName("Gryfindor");
        departmentEntityGryffindor.setId(2);
        departmentEntityGryffindor.setManager(employeeEntityHarry);
        departmentEntitySlytherin.setManager(employeeEntityTom);
    }

    @Test
    void itShould_FindAllDepartment() {
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
    void itShould_SaveDepartment() {
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

    @Test
    void itShould_throwErrorWhenDepartmentNotFound() {
        //Given
        Integer departmentId = 1;
        given(departmentRepository.findById(departmentId)).willReturn(Optional.ofNullable(null));
        //When
        //Then
        assertThatThrownBy(() -> {
            underTest.findById(departmentId);
        })
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining(String.format("Department Not Found departmentId : %s", departmentId));
    }

    @Test
    void itShould_findDepartmentById() {
        //Given
        Integer departmentId = departmentEntityGryffindor.getId();
        DepartmentDTO expectedValue = departmentMapper.EntityToDTO(departmentEntityGryffindor);
        given(departmentRepository.findById(departmentId)).willReturn(Optional.of(departmentEntityGryffindor));
        //When
        DepartmentDTO actualReturnVal = underTest.findById(departmentId);
        //Then
        Assertions.assertThat(actualReturnVal)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(expectedValue);
    }
}