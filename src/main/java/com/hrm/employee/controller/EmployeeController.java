package com.hrm.employee.controller;

import com.hrm.employee.model.Department;
import com.hrm.employee.model.Employee;
import com.hrm.employee.model.Manager;
import com.hrm.employee.service.DepartmentService;
import com.hrm.employee.service.EmployeeService;
import com.hrm.employee.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/showAllEmployees")
    public String showAllEmployees(Model model) {
        // create model attribute to bind form data
        int pageSize = 5;

        List<Employee> employee = employeeService.getAllEmployees();
        System.out.println(employee.size());
        // List < Employee > listEmployees = page.getContent();
        model.addAttribute("employee", employee);
        return "employees";
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        // create model attribute to bind form data
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new_employee";
    }

    @GetMapping("/showEmployees")
    public String showEmployees(Model model) {
        // create model attribute to bind form data
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employees";
    }


    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        // save employee to database
        employeeService.saveEmployee(employee);
        return "redirect:/showAllEmployees";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

        // get employee from the service
        Employee employee = employeeService.getEmployeeById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("employee", employee);
        return "update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {

        // call delete employee method
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/showAllEmployees";
    }
}