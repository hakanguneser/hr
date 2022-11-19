package com.avinty.hr.repository;

import com.avinty.hr.model.entity.DepartmentEntity;
import com.avinty.hr.model.entity.EmployeeEntity;
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

    @Test
    public void itShould_SelectNewEmployeeById() {
        //Given employee entity
        EmployeeEntity employee = EmployeeEntity.builder()
                .email("mail@mail.com")
                .password("123")
                .fullName("fullname")
                .department(DepartmentEntity.builder().name("HR").build())
                .build();
        //When
        EmployeeEntity savedEmployee = underTest.save(employee);

        //Then
        Optional<EmployeeEntity> foundEmployee = underTest.findById(savedEmployee.getId());

        assertThat(foundEmployee.get())
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(savedEmployee);
    }


    @Test
    public void itShould_findAllEmployees_worksForSameDepartment(){
        //Given department
        DepartmentEntity department = DepartmentEntity.builder().name("HR").build();
        //... employees with same department
        EmployeeEntity employee1 = EmployeeEntity.builder()
                .email("mail@mail.com")
                .password("123")
                .fullName("fullname")
                .department(department)
                .build();
        EmployeeEntity employee2 = EmployeeEntity.builder()
                .email("mail@mail.com")
                .password("123")
                .fullName("fullname")
                .department(department)
                .build();
        //.. extra employee works for different department
        EmployeeEntity employee3 = EmployeeEntity.builder()
                .email("mail@mail.com")
                .password("123")
                .fullName("fullname")
                .department(DepartmentEntity.builder().name("Research and Development").build())
                .build();
        //When
        EmployeeEntity savedEmployee1 = underTest.save(employee1);
        EmployeeEntity savedEmployee2 = underTest.save(employee2);
        EmployeeEntity savedEmployee3 = underTest.save(employee3);
        //Then
        List<EmployeeEntity> employeeList = underTest.findByDepartmentId(department.getId());

        assertThat(employeeList)
                .isNotNull()
                .hasSize(2)
                .contains(employee1,employee2)
                .doesNotContain(employee3);
    }
}