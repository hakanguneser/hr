package com.avinty.hr.service;

import com.avinty.hr.exception.EntityNotFoundException;
import com.avinty.hr.mapper.DepartmentMapper;
import com.avinty.hr.model.DTO.DepartmentDTO;
import com.avinty.hr.model.entity.DepartmentEntity;
import com.avinty.hr.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    private DepartmentMapper departmentMapper = Mappers.getMapper(DepartmentMapper.class);

    public List<DepartmentDTO> findAll() {
        return departmentRepository.findAll().stream()
                .map(departmentMapper::EntityToDTO)
                .collect(Collectors.toList());
    }

    public DepartmentDTO save(DepartmentDTO departmentDTO) {
        DepartmentEntity savedDepartment = departmentRepository.save(departmentMapper.DTOToEntity(departmentDTO));
        return departmentMapper.EntityToDTO(savedDepartment);
    }

    public DepartmentDTO findById(Integer departmentId) {
        return departmentMapper.EntityToDTO(findEntityById(departmentId));
    }

    public DepartmentEntity findEntityById(Integer departmentId) {
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("department Not Found");
                });
    }
}
