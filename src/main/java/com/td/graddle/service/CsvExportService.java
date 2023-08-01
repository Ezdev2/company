package com.td.graddle.service;

import com.td.graddle.model.Employee;
import com.td.graddle.repository.EmployeeRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Service
public class CsvExportService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeService employeeService;

    public CsvExportService(EmployeeRepository employeeRepository, EmployeeService employeeService) {
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
    }

    public void writeEmployeesToCsv(Writer writer, List<Employee> employees) {
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("ID", "Name", "Last Name", "Date of birth", "Department", "Address", "Telephone", "Position", "Number", "Gender", "Email personnel", "Email pro", "CIN", "CIN date issue", "CIN place issue", "Number of children", "Hire date", "Departure date", "Social Professional Category", "CNAPS number");
            for (Employee employee : employees) {
                csvPrinter.printRecord(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getDateOfBirth(), employee.getDepartment(), employee.getAddress(), employee.getPhone(), employee.getJobTitle(), employee.getEmployeeNumber(), employee.getGender(), employee.getPersonalEmail(), employee.getWorkEmail(), employee.getCinNumber(), employee.getCinIssueDate(), employee.getCinIssuePlace(), employee.getNumberOfChildren(), employee.getHireDate(), employee.getDepartureDate(), employee.getSocioProfessionalCategory(), employee.getCnapsNumber());
            }
        } catch (IOException e) {
        }
    }
}