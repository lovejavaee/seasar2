create table DEPARTMENT (
    ID integer generated by default as identity(start with 1),
    NAME varchar(255),
    constraint DEPARTMENT_PK primary key(ID)
);