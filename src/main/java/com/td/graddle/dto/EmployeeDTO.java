package com.td.graddle.dto;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeDTO {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String department;
    private String address;
    private String phone;
    private String jobTitle;
    private String employeeNumber;
    private String gender;
    private String personalEmail;
    private String workEmail;
    private String cinNumber;
    private LocalDate cinIssueDate;
    private String cinIssuePlace;
    private Integer numberOfChildren;
    private LocalDate hireDate;
    private LocalDate departureDate;
    private String socioProfessionalCategory;
    private String cnapsNumber;
}
