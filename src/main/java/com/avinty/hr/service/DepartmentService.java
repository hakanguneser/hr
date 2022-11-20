package com.avinty.hr.service;

import com.avinty.hr.mapper.DepartmentMapper;
import com.avinty.hr.model.DTO.DepartmentDTO;
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
}
