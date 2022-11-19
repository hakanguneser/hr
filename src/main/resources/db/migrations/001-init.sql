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