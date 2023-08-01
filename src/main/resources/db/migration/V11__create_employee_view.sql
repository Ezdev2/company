CREATE VIEW employee_view AS
SELECT * FROM employee
WHERE first_name LIKE '%:first_name%'
  AND last_name LIKE '%:last_name%'
  AND job_title LIKE '%:job_title%'
  AND gender LIKE '%:gender%'
ORDER BY first_name ASC, last_name ASC, job_title ASC;