DROP TABLE IF EXISTS EMPLOYEE;
DROP TABLE IF EXISTS DEPARTMENT;
drop table if exists role;
drop table if exists user;
drop table if exists user_roles;

create table role (
  id bigint not null auto_increment, 
  description varchar(255),
  name varchar(255), 
  primary key (id));
  
create table user (
  id bigint not null auto_increment, 
  age integer, 
  password varchar(255), 
  salary bigint, 
  username varchar(255), 
  primary key (id));


create table user_roles (
  user_id bigint not null, 
  role_id bigint not null, 
  primary key (user_id, role_id));


CREATE TABLE DEPARTMENT (
  department_id INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  department_name VARCHAR(250) NOT NULL
);

CREATE TABLE EMPLOYEE (
  employee_id INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  department_id INT(10),
  FOREIGN KEY (department_id) REFERENCES DEPARTMENT (department_id)
);
  
