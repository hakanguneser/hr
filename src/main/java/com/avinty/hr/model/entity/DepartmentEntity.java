package com.avinty.hr.model.entity;


import com.avinty.hr.model.entity.base.AbstractEntityBase;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@SuperBuilder
@Entity
@Table(name = "departments")
@Getter
@Setter
@ToString
@NoArgsConstructor
@SequenceGenerator(allocationSize = 1, name = "idgen", sequenceName = "SEQ_DEPARTMENT")
public class DepartmentEntity extends AbstractEntityBase {

    @Column(length = 100, nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "manager_id",referencedColumnName = "id")
    @JsonBackReference
    @ToString.Exclude
    private EmployeeEntity manager;
}
