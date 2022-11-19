package com.avinty.hr.repository;

import com.avinty.hr.model.entity.DepartmentEntity;
import com.avinty.hr.model.entity.EmployeeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(
        properties = {
                "spring.jpa.properties.javax.persistence.validation.mode=none"
        }
)
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository underTest;

    @Test
    public void itShouldSelectNewDepartmentById() {
        //Given employee entity
        EmployeeEntity employee = EmployeeEntity.builder()
                .password("123")
                .departmentId(123)
                .email("mail@mail.com").build();
        // ... department entity
        DepartmentEntity department = DepartmentEntity.builder()
                .name("Human&Resources")
                .manager(employee)
                .build();
        //When
        DepartmentEntity savedDepartment = underTest.save(department);
        //Then
        Optional<DepartmentEntity> foundDepartment = underTest.findById(savedDepartment.getId());

        assertThat(foundDepartment.get())
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(savedDepartment);
    }
}