package com.revature.dao;

import com.revature.models.Employee;

import java.util.List;

public interface EmployeeDAO {
    Employee getByUsername(String username);

    Employee createEmployee(String first, String last, String email, String username, String password);

    List<Employee> getAllEmployees();
}
