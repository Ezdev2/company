package com.td.graddle.repository;

import com.td.graddle.model.Employee;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

//@Repository
//public interface EmployeeRepository extends JpaRepository<Employee, Long> {
//
//}
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAll(Specification<Employee> specification);
//    List<Employee> findEmployeesByLastNameContainingAndFirstNameContainingAndGenderAndJobTitle(String lastName, String firstName, String gender, String jobTitle);
}