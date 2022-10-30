package com.hrm.employee.service;

import java.util.List;


import com.hrm.employee.model.Manager;
import org.springframework.data.domain.Page;

public interface ManagerService {
    List<Manager> getAllManagers();
    void saveManager(Manager manager);
    Manager getManagerById(long id);
    void deleteManagerById(long id);
    //Page< Manager > findPaginated(int pageNo, int pageSize);
    Page<Manager> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
