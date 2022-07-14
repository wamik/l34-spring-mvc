package org.example.service;

import org.example.model.Employee;
import org.example.store.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> listAll() {
        return repository.listAll();
    }

    public Employee getOne(Long id) {
        return repository.getOne(id);
    }

    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    public void delete(Long id) {
        repository.delete(id);
    }

}
