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
@Table(name = "employees")
@Getter
@Setter
@ToString
@NoArgsConstructor
@SequenceGenerator(allocationSize = 1, name = "idgen", sequenceName = "SEQ_EMPLOYEE")
public class EmployeeEntity extends AbstractEntityBase {
    @Column(nullable = false)
    private String email;
    @Column(length = 66, nullable = false)
    private String password;
    @Column(length = 200, nullable = false)
    private String fullName;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="dep_id", nullable=false)
    @JsonIgnore
    @ToString.Exclude
    private DepartmentEntity department;
}
