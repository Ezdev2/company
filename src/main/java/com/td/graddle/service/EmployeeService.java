package com.td.graddle.service;

import com.td.graddle.model.Employee;
import com.td.graddle.repository.EmployeeRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee not found with id: " + id));
    }

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void updateEmployee(Long id, Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Employee not found with id: " + id));
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setDateOfBirth(employee.getDateOfBirth());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setAddress(employee.getAddress());
        existingEmployee.setPhone(employee.getPhone());
        existingEmployee.setJobTitle(employee.getJobTitle());
        existingEmployee.setImagePath(employee.getImagePath());
        existingEmployee.setEmployeeNumber(employee.getEmployeeNumber());
        existingEmployee.setGender(employee.getGender());
        existingEmployee.setPersonalEmail(employee.getPersonalEmail());
        existingEmployee.setWorkEmail(employee.getWorkEmail());
        existingEmployee.setCinNumber(employee.getCinNumber());
        existingEmployee.setCinIssueDate(employee.getCinIssueDate());
        existingEmployee.setCinIssuePlace(employee.getCinIssuePlace());
        existingEmployee.setNumberOfChildren(employee.getNumberOfChildren());
        existingEmployee.setHireDate(employee.getHireDate());
        existingEmployee.setDepartureDate(employee.getDepartureDate());
        existingEmployee.setSocioProfessionalCategory(employee.getSocioProfessionalCategory());
        existingEmployee.setCnapsNumber(employee.getCnapsNumber());

        employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> filterEmployees(String lastName, String firstName, String gender, String jobTitle) {
        Specification<Employee> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(lastName)) {
                predicates.add(criteriaBuilder.like(root.get("lastName"), "%" + lastName + "%"));
            }

            if (StringUtils.hasText(firstName)) {
                predicates.add(criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%"));
            }

            if (StringUtils.hasText(gender)) {
                predicates.add(criteriaBuilder.equal(root.get("gender"), gender));
            }

            if (StringUtils.hasText(jobTitle)) {
                predicates.add(criteriaBuilder.like(root.get("jobTitle"), "%" + jobTitle + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return employeeRepository.findAll(specification);
    }

}