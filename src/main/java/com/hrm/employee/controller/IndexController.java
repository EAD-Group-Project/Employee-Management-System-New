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
public class IndexController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private DepartmentService departmentService;



    // display list of employees
    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "firstName", "asc", model);
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page < Employee > page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List < Employee > listEmployees = page.getContent();

        Page <Manager> page1 = managerService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List < Manager > listManagers = page1.getContent();

//        Page <Department> page2 = departmentService.findPaginated(pageNo, pageSize, sortField, sortDir);
//        List < Department > listDepartments = page2.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("currentPageM", pageNo);
        model.addAttribute("currentPageD", pageNo);

        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalPagesM", page1.getTotalPages());
        //model.addAttribute("totalPagesD", page2.getTotalPages());

        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("totalItemsM", page1.getTotalElements());
        //model.addAttribute("totalItemsD", page2.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listEmployees", listEmployees);
        model.addAttribute("listManagers", listManagers);
        //model.addAttribute("listDepartments", listDepartments);

        return "index";
    }

    @GetMapping("/page1/{pageNo}")
    public String findPaginatedM(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page < Employee > page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List < Employee > listEmployees = page.getContent();

        Page <Manager> page1 = managerService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List < Manager > listManagers = page1.getContent();

//        Page <Department> page2 = departmentService.findPaginated(pageNo, pageSize, sortField, sortDir);
//        List < Department > listDepartments = page2.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("currentPageM", pageNo);
        model.addAttribute("currentPageD", pageNo);

        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalPagesM", page1.getTotalPages());
        //model.addAttribute("totalPagesD", page2.getTotalPages());

        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("totalItemsM", page1.getTotalElements());
        //model.addAttribute("totalItemsD", page2.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listEmployees", listEmployees);
        model.addAttribute("listManagers", listManagers);
        //model.addAttribute("listDepartments", listDepartments);

        return "index";
    }
}
