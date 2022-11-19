package com.avinty.hr.repository;

import com.avinty.hr.model.entity.DepartmentEntity;
import com.avinty.hr.model.entity.EmployeeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(
        properties = {
                "spring.jpa.properties.javax.persistence.validation.mode=none"
        }
)
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository underTest;

    DepartmentEntity DepartmentSlytherin, departmentGryffindor;
    EmployeeEntity employeeSeverus, employeeHarry, employeeTom;

    @BeforeEach
    void setUp() {


        DepartmentSlytherin = DepartmentEntity.builder().name("Slytherin").build();
        departmentGryffindor = DepartmentEntity.builder().name("Gryffindor").build();
        employeeSeverus = EmployeeEntity.builder()
                .email("severus@hogwarts.com")
                .password("11")
                .fullName("Severus Snape")
                .department(DepartmentSlytherin)
                .build();
        employeeHarry = EmployeeEntity.builder()
                .email("harry@hogwarts.com")
                .password("22")
                .fullName("Harry Potter")
                .department(departmentGryffindor)
                .build();
        employeeTom = EmployeeEntity.builder()
                .email("tom@hogwarts.com")
                .password("33")
                .fullName("Tom Riddle")
                .department(DepartmentSlytherin)
                .build();

    }

    @Test
    public void itShould_SelectNewEmployeeById() {

        //When
        EmployeeEntity savedEmployee = underTest.save(employeeHarry);

        //Then
        Optional<EmployeeEntity> foundEmployee = underTest.findById(savedEmployee.getId());

        assertThat(foundEmployee.get())
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(savedEmployee);
    }


    @Test
    public void itShould_findAllEmployees_worksForSameDepartment() {

        //given saved employees
        underTest.save(employeeSeverus);
        underTest.save(employeeHarry);
        underTest.save(employeeTom);
        //Then
        List<EmployeeEntity> employeeList = underTest.findByDepartmentId(DepartmentSlytherin.getId());

        assertThat(employeeList)
                .isNotNull()
                .hasSize(2)
                .contains(employeeSeverus, employeeTom)
                .doesNotContain(employeeHarry);
    }
}