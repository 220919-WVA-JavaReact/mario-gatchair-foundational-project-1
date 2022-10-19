package com.revature.service;
import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImplPostgres;
import com.revature.models.Employee;

import java.util.List;

public class EmployeeServiceAPI {

    EmployeeDAO employd = new EmployeeDAOImplPostgres();

    public Employee login(String username, String password) {
        Employee employee = employd.getByUsername(username);
        if (password.equals(employee.getPassword())) {
            System.out.println("You've been logged in!");
            return employee;
        } else {
            System.out.println("Login attempt has failed. Please try again.");
            return null;
        }
    }
    public Employee register(String first, String last, String email, String username, String password) {
        return employd.createEmployee(first, last, email, username, password);
    }

    public List<Employee> getAllEmployees(Employee loggedE){
        return employd.getAllEmployees(loggedE);
    }
}
