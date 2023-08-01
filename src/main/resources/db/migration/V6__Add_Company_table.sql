create table companies (
    id SERIAL PRIMARY KEY,
    name varchar(255) not null,
    description varchar(255),
    slogan varchar(255),
    email varchar(255),
    address varchar(255),
    nif varchar(255),
    stat varchar(255),
    rcs varchar(255),
    phones TEXT,
    logo varchar(255)
);