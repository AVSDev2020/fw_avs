delete  from tbl_orders;
delete  from tbl_employee;
delete  from tbl_department;


insert into tbl_department(name) values ('sales');
insert into tbl_department(name) values ('developer');
insert into tbl_department(name) values ('tester');

insert into tbl_employee(name, gender, email, fk_department_id) VALUES ('alex','0','alexjkd@outlook.com',1);

insert into tbl_orders(name,fk_employee_id) VALUES('alex-order1',1);
insert into tbl_orders(name,fk_employee_id) VALUES('alex-order2',1);
insert into tbl_orders(name,fk_employee_id) VALUES('alex-order3',1);
insert into tbl_orders(name,fk_employee_id) VALUES('alex-order4',1);