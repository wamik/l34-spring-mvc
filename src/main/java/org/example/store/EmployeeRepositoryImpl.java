package org.example.store;

import org.example.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

//    private static EmployeeRepositoryImpl instance;
    private final Map<Long, Employee> store = new HashMap();

//    public static EmployeeRepositoryImpl getInstance() {
//        if (instance == null) {
//            instance = new EmployeeRepositoryImpl();
//            System.out.println("Repository is null. Creating one");
//        }
//        System.out.println("Repository is NOT null. Returning one");
//        return instance;
//    }

    @Override
    public List<Employee> listAll() {
        return new ArrayList(store.values());
    }

    @Override
    public Employee getOne(long id) {
        return store.get(id);
    }

    @Override
    public Employee save(Employee employee) {
        long id;

        if (employee.getId() == null) {
            id = store.size() + 1;
            employee.setId(id);
        } else {
            id = employee.getId();
        }

        store.put(id, employee);

        return getOne(id);
    }


    @Override
    public void delete(long id) {
        store.remove(id);
    }
}
