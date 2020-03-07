DROP TABLE IF EXISTS tbl_orders;
DROP TABLE IF EXISTS  tbl_employee;
DROP TABLE IF EXISTS tbl_department;

CREATE TABLE IF NOT EXISTS tbl_department (	id SERIAL PRIMARY KEY,
                                               name varchar
);
CREATE TABLE IF NOT EXISTS tbl_employee (	id SERIAL PRIMARY KEY,
                                             name varchar,
                                             gender boolean,
                                             email varchar,
                                             fk_department_id bigint
);
alter table tbl_employee add constraint tbl_employee_fk_department_id_fk0  foreign key (fk_department_id) references tbl_department (id);

CREATE TABLE IF NOT EXISTS tbl_orders (	id SERIAL PRIMARY KEY,
                                           name varchar,
                                           fk_employee_id bigint
);
alter table tbl_orders add constraint tbl_orders_fk_employee_id_fk0  foreign key (fk_employee_id) references tbl_employee (id);
