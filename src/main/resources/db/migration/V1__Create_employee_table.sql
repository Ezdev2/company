create table employee (
    id SERIAL PRIMARY KEY,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    date_of_birth date not null,
    department varchar(255) not null,
    address varchar(255) not null,
    phone varchar(255) not null,
    job_title varchar(255) not null
);