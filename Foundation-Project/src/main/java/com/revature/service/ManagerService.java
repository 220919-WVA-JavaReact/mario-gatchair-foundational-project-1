package com.revature.service;

import com.revature.dao.ManagerDAO;
import com.revature.dao.ManagerDAOImplPostgres;
import com.revature.models.Employee;
import com.revature.models.Manager;

import java.util.Scanner;

public class ManagerService {
    ManagerDAO mn = new ManagerDAOImplPostgres();

    Scanner sc = new Scanner(System.in);

    public Manager login(){

        System.out.println("Login");
        System.out.println("-------------------------------------------------");
        System.out.println("Please enter your username");
        String username = sc.nextLine();
        System.out.println("Please enter your password");
        String password = sc.nextLine();

        Manager admin = mn.getByUsername(username);

        if (admin.getPassword().equals(password)){
            System.out.println("You're signed in Welcome!");
            System.out.println("Welcome " + admin.getName() + "! What do you want to do today?");
            System.out.println(admin);

            return admin;
        } else{
            System.out.println("Invalid info please check your credentials!");

            return null;
        }
    }

    public Manager register() {
        System.out.println("Register");
        System.out.println("-------------------------------------------------");
        System.out.println("Please enter your name");
        String name = sc.nextLine();
        System.out.println("Please enter your email");
        String email = sc.nextLine();
        System.out.println("Please enter your username");
        String username = sc.nextLine();
        System.out.println("Please enter your password");
        String password = sc.nextLine();

        Manager manager = mn.createManager(name, email, username, password);
        return manager;
    }
}
