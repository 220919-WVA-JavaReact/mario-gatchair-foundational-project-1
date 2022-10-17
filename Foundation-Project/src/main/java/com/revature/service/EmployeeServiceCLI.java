package com.revature.service;
import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImplPostgres;
import com.revature.models.Employee;

import java.util.Scanner;

public class EmployeeServiceCLI {


    EmployeeDAO ed = new EmployeeDAOImplPostgres();

    Scanner sc = new Scanner(System.in);

    public Employee login(){

        System.out.println("Login");
        System.out.println("*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
        System.out.println("Please enter your username");
        String username = sc.nextLine();
        System.out.println("Please enter your password");
        String password = sc.nextLine();

        Employee user = ed.getByUsername(username);

        if (user != null && user.getPassword().equals(password)){
            System.out.println("You're signed in Welcome!");
            System.out.println("Welcome " + user.getFirst() + "! What do you want to do today?");

            return user;
        }
        return null;
    }

    public Employee register(){
        System.out.println("Let's get startred with your Registration");
        System.out.println("*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
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

        Employee employee = ed.createEmployee(first, last, email, username, password);
        if(employee.getEmployeeId() != 0){
            System.out.println("Thank you so much for registering!");
            System.out.println("*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            return employee;
        }
        return null;
    }
}
