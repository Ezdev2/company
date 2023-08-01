package com.td.graddle.controller;

import com.td.graddle.dto.EmployeeDTO;
import com.td.graddle.model.Company;
import com.td.graddle.model.Employee;
import com.td.graddle.service.CompanyService;
import com.td.graddle.service.CsvExportService;
import com.td.graddle.service.EmployeeService;
import com.td.graddle.utils.ObjectMapperUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;
    private final CompanyService companyService;
    private final CsvExportService csvExportService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, CompanyService companyService, CsvExportService csvExportService) {
        this.employeeService = employeeService;
        this.companyService = companyService;
        this.csvExportService = csvExportService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();

        for (Employee employee : employees) {
            Long companyId = Long.valueOf(1);
//            Long companyId = employee.getCompany().getId();

            Company company = companyService.getCompanyById(companyId);
            employee.setCompany(company);
        }

        model.addAttribute("employees", employees);

        return "index";
    }

    @GetMapping("/employees/add")
    public String addEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "add_employee";
    }

    private String saveMultipartFileAndGetPath(MultipartFile imageData) throws IOException {
        if (imageData != null && !imageData.isEmpty()) {
            String fileName = UUID.randomUUID().toString() + "-" + imageData.getOriginalFilename();
            String uploadDir = "images/";
            Path filePath = Paths.get(uploadDir, fileName);

            Files.copy(imageData.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } else {
            return null;
        }
    }

    @PostMapping(value = "/employees/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addEmployee(@ModelAttribute EmployeeDTO employeeDTO, @RequestParam("imagePath") MultipartFile imageData, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "error-page";
        }

        try {
            String imagePath = saveMultipartFileAndGetPath(imageData);
            if (imagePath != null) {
                Employee employee = ObjectMapperUtils.map(employeeDTO, Employee.class);
                employee.setImagePath(imagePath);
                employeeService.saveEmployee(employee);
            }
        } catch (IOException e) {
        }
        return "redirect:/";
    }

    @GetMapping("/employees/edit/{id}")
    public String editEmployeeForm(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "edit_employee";
    }

    @PostMapping("/employees/edit/{id}")
    public String editEmployee(@PathVariable Long id, @ModelAttribute EmployeeDTO employeeDTO, @RequestParam("imagePath") MultipartFile imageData, BindingResult bindingResult) {
//        employeeService.updateEmployee(id, employee);
//        return "redirect:/";
        if (bindingResult.hasErrors()) {
            return "error-page";
        }

        try {
            String imagePath = saveMultipartFileAndGetPath(imageData);
            if (imagePath != null) {
                Employee employee = ObjectMapperUtils.map(employeeDTO, Employee.class);
                employee.setImagePath(imagePath);
                employeeService.updateEmployee(id, employee);
            }
        } catch (IOException e) {
        }
        return "redirect:/";
    }

    @GetMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/";
    }

    @GetMapping("/employees/details/{id}")
    public String viewEmployeeDetails(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "details_employee";
    }

    @GetMapping("/employees/filter")
    public String filterEmployees(
            @RequestParam(name = "lastName", required = false) String lastName,
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "gender", required = false) String gender,
            @RequestParam(name = "jobTitle", required = false) String jobTitle,
            Model model) {

        List<Employee> filteredEmployees = employeeService.filterEmployees(lastName, firstName, gender, jobTitle);

        model.addAttribute("employees", filteredEmployees);

        return "index";
    }

    @GetMapping("/employees/export")
    public void exportToCSV(@RequestParam(name = "lastName", required = false) String lastName,
                            @RequestParam(name = "firstName", required = false) String firstName,
                            @RequestParam(name = "gender", required = false) String gender,
                            @RequestParam(name = "jobTitle", required = false) String jobTitle,
                            HttpServletResponse response) throws IOException {

        List<Employee> filteredEmployees = employeeService.filterEmployees(lastName, firstName, gender, jobTitle);

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=employees.csv");
        csvExportService.writeEmployeesToCsv(response.getWriter(), filteredEmployees);
    }

}
