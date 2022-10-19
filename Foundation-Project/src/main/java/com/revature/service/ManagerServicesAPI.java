package com.revature.service;

import com.revature.dao.ManagerDAO;
import com.revature.dao.ManagerDAOImplPostgres;
import com.revature.models.Manager;

public class ManagerServicesAPI {
    ManagerDAO managed = new ManagerDAOImplPostgres();

    public Manager login(String username, String password){
        Manager manager = managed.getByUsername(username);
        if (password.equals(manager.getPassword())){
            System.out.println("Welcome back, you're signed in!");
            return manager;
        } else{
            System.out.println("Only authorized users are allowed in. Try again");
            return null;
        }
    }

    public Manager register(String first, String last,String email, String username, String password){
        return managed.createManager(first, last, email, username, password);
    }
}
