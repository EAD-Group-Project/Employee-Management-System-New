package com.hrm.employee.service;

import java.util.List;
import java.util.Optional;

import com.hrm.employee.model.Department;
import com.hrm.employee.model.Employee;
import com.hrm.employee.model.Manager;
import com.hrm.employee.repository.DepartmentRepository;
import com.hrm.employee.repository.EmployeeRepository;
import com.hrm.employee.repository.EmployeeRepository;
import com.hrm.employee.repository.ManagerRepository;
import com.hrm.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List <Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public void saveDepartment(Department department) {
        this.departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentById(long id) {
        Optional< Department > optional = departmentRepository.findById(id);
        Department department = null;
        if (optional.isPresent()) {
            department = optional.get();
        } else {
            throw new RuntimeException(" Employee not found for id :: " + id);
        }
        return department;
    }

    @Override
    public void deleteDepartmentById(long id) {
        this.departmentRepository.deleteById(id);
    }

//    @Override
//    public Page<Manager> findPaginated(int pageNo, int pageSize) {
//        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
//        return this.managerRepository.findAll(pageable);
//    }

    @Override
    public Page<Department> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.departmentRepository.findAll(pageable);
    }
}


