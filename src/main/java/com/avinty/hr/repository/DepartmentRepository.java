package com.avinty.hr.repository;

import com.avinty.hr.model.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Integer> {
    List<DepartmentEntity> findByNameContainsIgnoreCase(String name);
}
