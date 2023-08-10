DROP DATABASE IF EXISTS employeeRepo;

CREATE DATABASE employeeRepo;

USE employeeRepo;

CREATE TABLE employee(
id int NOT NULL AUTO_INCREMENT,
first_name varchar(50) NOT NULL,
last_name varchar(50),
email varchar(50) NOT NULL,

PRIMARY KEY(id),
UNIQUE(email)
);

INSERT INTO employee(first_name,last_name,email) values
('Shubhpreet','saini','sainishubhpreet@gmail.com'),
('Navpreet','saini','nav@yahoo.com'),
('Sabay','Kaur','taliban@gmail.com'),
('Rohit','Shitty','shitty@gmail.com');
