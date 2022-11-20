package com.avinty.hr.service;

import com.avinty.hr.mapper.EmployeeMapper;
import com.avinty.hr.model.DTO.EmployeeDTO;
import com.avinty.hr.model.entity.DepartmentEntity;
import com.avinty.hr.model.entity.EmployeeEntity;
import com.avinty.hr.model.request.EmployeeRequest;
import com.avinty.hr.repository.EmployeeRepository;
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

class EmployeeServiceTest {

    private EmployeeService underTest;
    private EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private DepartmentService departmentService;
    @Captor
    private ArgumentCaptor<EmployeeEntity> employeeEntityArgumentCaptor;
    DepartmentEntity departmentSlytherin, departmentGryffindor;
    EmployeeEntity employeeEntitySeverus, employeeEntityHarry, employeeEntityTom;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        underTest = new EmployeeService(employeeRepository,departmentService);

        departmentSlytherin = DepartmentEntity.builder().id(1).name("Slytherin").build();
        departmentGryffindor = DepartmentEntity.builder().id(2).name("Gryffindor").build();
        employeeEntitySeverus = EmployeeEntity.builder()
                .id(1)
                .email("severus@hogwarts.com")
                .password("11")
                .fullName("Severus Snape")
                .department(departmentSlytherin)
                .build();
        employeeEntityHarry = EmployeeEntity.builder()
                .id(2)
                .email("harry@hogwarts.com")
                .password("22")
                .fullName("Harry Potter")
                .department(departmentGryffindor)
                .build();
        employeeEntityTom = EmployeeEntity.builder()
                .id(3)
                .email("tom@hogwarts.com")
                .password("33")
                .fullName("Tom Riddle")
                .department(departmentSlytherin)
                .build();
        departmentGryffindor.setManager(employeeEntityHarry);
        departmentSlytherin.setManager(employeeEntityTom);
    }

    @Test
    void itShould_FindAll() {
        //Given saved employees
        List<EmployeeEntity> employeeList = Arrays.asList(employeeEntitySeverus, employeeEntityHarry, employeeEntityTom);

        given(employeeRepository.findAll()).willReturn(employeeList);
        //When
        List<EmployeeDTO> allReturnedEmployeeList = underTest.findAll();
        //Then
        Assertions.assertThat(allReturnedEmployeeList)
                .isNotNull()
                .hasSize(3)
                .contains(employeeMapper.EntityToDTO(employeeEntitySeverus),
                        employeeMapper.EntityToDTO(employeeEntityHarry),
                        employeeMapper.EntityToDTO(employeeEntityTom));
    }

    @Test
    void itShould_saveEmployee() {
        //Given saved employees
        EmployeeRequest request = EmployeeRequest.builder()
                .departmentId(departmentGryffindor.getId())
                .email(employeeEntityHarry.getEmail())
                .fullName(employeeEntityHarry.getFullName())
                .password(employeeEntityHarry.getPassword())
                .build();
        given(departmentService.findEntityById(departmentGryffindor.getId()))
                .willReturn(departmentGryffindor);
        //When
        underTest.save(request);
        //Then
        then(employeeRepository).should().save(employeeEntityArgumentCaptor.capture());

        String value = employeeEntityArgumentCaptor.getValue().getEmail();

        Assertions.assertThat(value)
                .isNotNull()
                .isEqualTo(request.getEmail());
    }
}