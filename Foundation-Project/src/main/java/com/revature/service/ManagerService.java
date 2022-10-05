package com.revature.service;

import com.revature.dao.ManagerDAO;
import com.revature.dao.ManagerDAOImpl;
import com.revature.models.Manager;

import java.util.Scanner;

public class ManagerService {
    ManagerDAO mn = new ManagerDAOImpl();

    Scanner sc = new Scanner(System.in);

    public Manager login(){

        System.out.println("Login----");
        System.out.println("Please enter your username");
        String username = sc.nextLine();
        System.out.println("Please enter your password");
        String password = sc.nextLine();

        Manager admin = mn.getByUsername(username);

        if (admin.getPassword().equals(password)){
            System.out.println("You're signed in Welcome!");
            System.out.println(admin);

            return admin;
        } else{
            System.out.println("Invalid info please check your credentials!");

            return null;
        }
    }
}
