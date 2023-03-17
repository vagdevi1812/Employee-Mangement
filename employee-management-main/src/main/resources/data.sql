INSERT INTO user (id, username, password, salary, age) VALUES (1, 'user1', '$2a$04$1amIloeuNXIbLnegENsniu/VrQ4YmYwyf5cj10iatdTUtG8oVjZrK', 3456, 33);
INSERT INTO user (id, username, password, salary, age) VALUES (2, 'user2', '$2a$04$..zQWrVogr1JA8App.fGCuNhlTya6xx66HmSRYGAG0fTNICmpbUKy', 7823, 23);
INSERT INTO user (id, username, password, salary, age) VALUES (3, 'user3', '$2a$04$FIeTBG9kSQFtw4wJIgJNKuTmtFqnBKNPGO9sxm3Zau4dQlewnQz.S', 4234, 45);

INSERT INTO role (id, description, name) VALUES (4, 'Admin role', 'ADMIN');
INSERT INTO role (id, description, name) VALUES (5, 'User role', 'USER');

INSERT INTO user_roles (user_id, role_id) VALUES (1, 4);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 5);


INSERT INTO DEPARTMENT (department_name) VALUES
  ('HR'),
  ('Finance'),
  ('IT');

INSERT INTO EMPLOYEE (first_name, last_name, department_id) VALUES
  ('Rithish', 'Ram', 1),
  ('Jenith', 'Leon', 2),
  ('Sagul', 'Hameed', 3);