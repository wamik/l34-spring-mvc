package org.example.store;


import org.example.model.Employee;

import java.util.List;

public interface EmployeeRepository {

    List<Employee> listAll();

    Employee getOne(long id);

    Employee save(Employee employee);

    void delete(long id);
}
