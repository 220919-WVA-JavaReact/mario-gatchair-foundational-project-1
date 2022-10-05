package com.revature.service;

import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImpl;
import com.revature.models.Employee;

import java.util.Scanner;

public class EmployeeService {


    EmployeeDAO ed = new EmployeeDAOImpl();

    Scanner sc = new Scanner(System.in);

    public Employee login(){

        System.out.println("Login----");
        System.out.println("Please enter your username");
        String username = sc.nextLine();
        System.out.println("Please enter your password");
        String password = sc.nextLine();

        Employee user = ed.getByUsername(username);

        if (user.getPassword().equals(password)){
            System.out.println("You're signed in Welcome!");
            System.out.println(user);

            return user;
        } else{
            System.out.println("Invalid info please check your credentials or register");

            return null;
        }
    }

    public Employee register(){
        System.out.println("Register----");
        System.out.println("Please enter your first name");
        String first = sc.nextLine();
        System.out.println("Please enter your last name");
        String last = sc.nextLine();
        System.out.println("Please enter your email");
        String email = sc.nextLine();
        System.out.println("Please enter your username");
        String username = sc.nextLine();
        System.out.println("Please enter your password");
        String password = sc.nextLine();

        Employee employee = ed.createEmployee(first, last, username, password);
        return employee;
    }
}
