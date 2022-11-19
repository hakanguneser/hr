package com.avinty.hr.repository;

import com.avinty.hr.model.entity.EmployeeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
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
        EmployeeEntity employeeEntity = new EmployeeEntity();
        int id = 1;
        employeeEntity.setId(id);
        employeeEntity.setEmail("hakan@mail.com");
        employeeEntity.setPassword("123");
        employeeEntity.setFullName("hakan");
        employeeEntity.setDepartmentId(123);

        EmployeeEntity savedEmployee = underTest.save(employeeEntity);

        Optional<EmployeeEntity> foundEmployee = underTest.findById(id);

        assertThat(foundEmployee.get())
                .isNotNull()
                .usingRecursiveComparison()
                .ignoringFields("createdAt","createdBy","updatedAt","updatedBy")
                .isEqualTo(savedEmployee);
    }

}