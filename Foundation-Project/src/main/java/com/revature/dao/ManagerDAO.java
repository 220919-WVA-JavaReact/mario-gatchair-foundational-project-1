package com.revature.dao;

import com.revature.models.Manager;

import java.util.List;

public interface ManagerDAO {
    Manager getByUsername(String username);

    Manager createManager(String first, String last, String email, String username, String password);

    List<Manager> getManager();

    //Manager demoteMananger(int id, String name, String email, String username, String password);
}