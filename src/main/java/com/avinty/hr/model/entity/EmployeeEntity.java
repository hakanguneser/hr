package com.avinty.hr.model.entity;

import com.avinty.hr.model.entity.base.AbstractEntityBase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@SuperBuilder
@Entity
@Table(name = "employees")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@SequenceGenerator(allocationSize = 1, name = "idgen", sequenceName = "SEQ_EMPLOYEE")
public class EmployeeEntity extends AbstractEntityBase {
    @Column(nullable = false)
    private String email;
    @Column(length = 66, nullable = false)
    private String password;
    @Column(length = 200, nullable = false)
    private String fullName;

    @Column(name="dep_id")
    private Integer departmentId;
}
