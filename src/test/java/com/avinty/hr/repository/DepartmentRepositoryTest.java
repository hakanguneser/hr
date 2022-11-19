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
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void itShouldSelectNewDepartmentById() {
        int departmentId = 1;

        EmployeeEntity employee = new EmployeeEntity();
        employee.setDepartmentId(departmentId);
        employee.setPassword("123");
        employee.setEmail("mail@mail.com");
        employee.setFullName("hakan");

        DepartmentEntity department = new DepartmentEntity();
        department.setId(departmentId);
        department.setName("Human&Resources");
        department.setManager(employee);

        employeeRepository.save(employee);
        DepartmentEntity savedDepartment = underTest.save(department);

        Optional<DepartmentEntity> foundDepartment = underTest.findById(departmentId);

        assertThat(foundDepartment.get())
                .isNotNull()
                .usingRecursiveComparison()
                .ignoringFields("createdAt", "createdBy", "updatedAt", "updatedBy")
                .isEqualTo(savedDepartment);
    }
}