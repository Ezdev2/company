ALTER TABLE employee
ADD COLUMN employee_number VARCHAR(255),
ADD COLUMN gender VARCHAR(10),
ADD COLUMN personal_email VARCHAR(255),
ADD COLUMN work_email VARCHAR(255),
ADD COLUMN cin_number VARCHAR(20),
ADD COLUMN cin_issue_date DATE,
ADD COLUMN cin_issue_place VARCHAR(255),
ADD COLUMN number_of_children INTEGER,
ADD COLUMN hire_date DATE,
ADD COLUMN departure_date DATE,
ADD COLUMN socio_professional_category VARCHAR(100),
ADD COLUMN cnaps_number VARCHAR(50);