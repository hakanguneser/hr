package com.avinty.hr.repository;

import com.avinty.hr.model.entity.DepartmentEntity;
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
        DepartmentEntity departmentEntity = new DepartmentEntity();

        int id = 1;
        departmentEntity.setId(id);
        departmentEntity.setName("Hakan");
        departmentEntity.setManagerId(3);

        DepartmentEntity savedDepartment = underTest.save(departmentEntity);

        Optional<DepartmentEntity> foundDepartment = underTest.findById(id);

        assertThat(foundDepartment.get())
                .isNotNull()
                .usingRecursiveComparison()
                .ignoringFields("createdAt", "createdBy", "updatedAt", "updatedBy")
                .isEqualTo(savedDepartment);
    }
}