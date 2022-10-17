package com.revature.service;
import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImplPostgres;
import com.revature.models.Employee;

import java.util.List;

public class EmployeeServiceAPI {

    EmployeeDAO employd = new EmployeeDAOImplPostgres();

    public String login(String username, String password) {
        boolean userExists = employd.existingEmployee(username);
        if (!userExists) {
            return "username";
        }
        Employee employee = employd.getByUsername("username");
        if (password.equals(employee.getPassword())) {
            System.out.println("You've signed in successfully.");
            return employee.toJsonString();
        } else {
            System.out.println("Login attempt has failed. Please try again.");
            return "password";
        }
    }

    public Employee register(String first, String last, String email, String username, String password) {
        return employd.createEmployee(first, last, email, username, password);
    }

    public List<Employee> getAllEmployees(Employee loggedE){
        return employd.getAllEmployees(loggedE);
    }
}
