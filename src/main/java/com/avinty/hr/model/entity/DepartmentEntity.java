package com.avinty.hr.model.entity;


import com.avinty.hr.model.entity.base.AbstractEntityBase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@SuperBuilder
@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(allocationSize = 1, name = "idgen", sequenceName = "SEQ_DEPARTMENT")
public class DepartmentEntity extends AbstractEntityBase {

    @Column(length = 100, nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "manager_id",referencedColumnName = "id")
    private EmployeeEntity manager;
}
