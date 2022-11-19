--liquibase formatted sql

--changeset nvoxland:1
create table EMPLOYEES
(
    id         int primary key,
    email      varchar(255) not null,
    password   varchar(66)  not null,
    full_name  varchar(200) not null,
    dep_id     int,
    created_at date,
    created_by int,
    updated_at date,
    updated_by int
);
create sequence SEQ_EMPLOYEE;

--changeset nvoxland:2
create table DEPARTMENTS
(
    id         int primary key,
    name       varchar(100) not null,
    manager_id int,
    created_at date,
    created_by int,
    updated_at date,
    updated_by int
);

create sequence SEQ_DEPARTMENT;