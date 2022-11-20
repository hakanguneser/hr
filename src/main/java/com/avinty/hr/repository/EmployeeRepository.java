package com.avinty.hr.repository;

import com.avinty.hr.model.entity.DepartmentEntity;
import com.avinty.hr.model.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    List<EmployeeEntity> findByDepartmentId(Integer departmentId);

    @Modifying
    @Query("update EmployeeEntity e set e.department.id = :newDepartmentId where e.department.id = :oldDepartmentId")
    void updateEmployeesDepartment(@Param("oldDepartmentId") Integer oldDepartmentId,
                                   @Param("newDepartmentId") Integer newDepartmentId);

}
