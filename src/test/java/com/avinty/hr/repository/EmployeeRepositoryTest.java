package com.avinty.hr.repository;

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
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository underTest;

    @Test
    public void itShouldSelectNewEmployeeById() {
        //Given employee entity
        EmployeeEntity employee = EmployeeEntity.builder()
                .email("mail@mail.com")
                .password("123")
                .fullName("fullname")
                .departmentId(123)
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

}