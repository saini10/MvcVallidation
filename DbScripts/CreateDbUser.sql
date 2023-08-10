DROP USER IF EXISTS 'employee'@'localhost';

CREATE USER 'employee'@'localhost' IDENTIFIED BY 'employee';

GRANT ALL PRIVILEGES ON employeeRepo.* TO 'employee'@'localhost'; 

