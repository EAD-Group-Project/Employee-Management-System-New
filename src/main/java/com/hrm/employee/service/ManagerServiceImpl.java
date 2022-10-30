package com.hrm.employee.service;

import java.util.List;
import java.util.Optional;

import com.hrm.employee.model.Manager;
import com.hrm.employee.repository.ManagerRepository;
import com.hrm.employee.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public List < Manager > getAllManagers() {
        return managerRepository.findAll();
    }

    @Override
    public void saveManager(Manager manager) {
        this.managerRepository.save(manager);
    }

    @Override
    public Manager getManagerById(long id) {
        Optional< Manager > optional = managerRepository.findById(id);
        Manager manager = null;
        if (optional.isPresent()) {
            manager = optional.get();
        } else {
            throw new RuntimeException(" Employee not found for id :: " + id);
        }
        return manager;
    }

    @Override
    public void deleteManagerById(long id) {
        this.managerRepository.deleteById(id);
    }

//    @Override
//    public Page<Manager> findPaginated(int pageNo, int pageSize) {
//        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
//        return this.managerRepository.findAll(pageable);
//    }

    @Override
    public Page<Manager> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.managerRepository.findAll(pageable);
    }
}
