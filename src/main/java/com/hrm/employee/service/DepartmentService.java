package com.hrm.employee.service;

import java.util.List;

import com.hrm.employee.model.Department;
import com.hrm.employee.model.Employee;
import com.hrm.employee.model.Manager;
import org.springframework.data.domain.Page;

public interface DepartmentService {
    List<Department> getAllDepartments();
    void saveDepartment(Department department);
    Department getDepartmentById(long id);
    void deleteDepartmentById(long id);
    //Page< Department > findPaginated(int pageNo, int pageSize);
    Page<Department> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}


