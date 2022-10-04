package com.revature.service;

import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImpl;
import com.revature.models.Employee;

public class EmployeeService {
    EmployeeDAO ed = new EmployeeDAOImpl();

    public void login(String username, String password){

        Employee employ = ed.getByUsername(username);

        if (employ.getPassword().equals(password)){
            System.out.println("You're signed in Welcome!");
            System.out.println(employ);
        } else{
            System.out.println("Invalid info please check your credentials or register");
        }
    }
}
