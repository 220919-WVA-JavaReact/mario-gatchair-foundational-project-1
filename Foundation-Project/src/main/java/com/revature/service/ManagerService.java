package com.revature.service;

import com.revature.dao.ManagerDAO;
import com.revature.dao.ManagerDAOImpl;
import com.revature.models.Manager;

public class ManagerService {
    ManagerDAO mn = new ManagerDAOImpl();

    public void login(String username, String password){

        Manager admin = mn.getByUsername(username);

        if (admin.getPassword().equals(password)){
            System.out.println("You're signed in Welcome!");
            System.out.println(admin);
        } else{
            System.out.println("Invalid info please check your credentials!");
        }
    }
}
