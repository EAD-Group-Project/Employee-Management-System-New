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
public class ManagerController {

    @Autowired
    private ManagerService managerService;


    @GetMapping("/showAllManagers")
    public String showAllManagers(Model model) {
        // create model attribute to bind form data
        int pageSize = 5;

        List<Manager> manager = managerService.getAllManagers();
        System.out.println(manager.size());
        // List < Employee > listEmployees = page.getContent();
        model.addAttribute("manager", manager);
        return "managers";
    }


    @GetMapping("/showNewManagerForm")
    public String showNewManagerForm(Model model) {
        // create model attribute to bind form data
        Manager manager = new Manager();
        model.addAttribute("manager", manager);
        return "new_manager";
    }


    @GetMapping("/showManagers")
    public String showManagers(Model model) {
        // create model attribute to bind form data
        Manager manager = new Manager();
        model.addAttribute("manager", manager);
        return "managers";
    }


    @PostMapping("/saveManager")
    public String saveManager(@ModelAttribute("manager") Manager manager) {
        // save employee to database
        managerService.saveManager(manager);
        //return "redirect:/showManagers";
        return "redirect:/showAllManagers";
    }


    @GetMapping("/showFormForUpdateM/{id}")
    public String showFormForUpdateM(@PathVariable(value = "id") long id, Model model) {

        // get employee from the service
        Manager manager = managerService.getManagerById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("manager", manager);
        return "update_manager";
    }


    @GetMapping("/deleteManager/{id}")
    public String deleteManager(@PathVariable(value = "id") long id) {

        // call delete employee method
        this.managerService.deleteManagerById(id);
        return "redirect:/showAllManagers";
    }
}
