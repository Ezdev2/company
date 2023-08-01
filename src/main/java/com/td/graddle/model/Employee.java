package com.td.graddle.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Table(name = "\"employee\"")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String department;
    private String address;
    private String phone;
    private String jobTitle;
    private String imagePath;
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

    @ManyToOne
    @JoinColumn(name = "company")
    private Company company;

    public String toCsv() {
        return lastName + "," +
                firstName + "," +
                dateOfBirth + "," +
                department + "," +
                address + "," +
                phone + "," +
                jobTitle + "," +
                employeeNumber + "," +
                gender + "," +
                personalEmail + "," +
                workEmail + "," +
                cinNumber + "," +
                cinIssueDate + "," +
                cinIssuePlace + "," +
                numberOfChildren + "," +
                hireDate + "," +
                departureDate + "," +
                socioProfessionalCategory + "," +
                cnapsNumber;
    }

    public String getCompanyName() {
        if (company != null) {
            return company.getName();
        }
        return "";
    }
}