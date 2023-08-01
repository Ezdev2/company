ALTER TABLE employee ADD COLUMN company_id BIGINT;

ALTER TABLE employee
ADD CONSTRAINT fk_company_id
FOREIGN KEY (company_id)
REFERENCES companies(id);