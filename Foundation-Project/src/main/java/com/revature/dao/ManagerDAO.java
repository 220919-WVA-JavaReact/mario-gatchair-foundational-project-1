package com.revature.dao;

import com.revature.models.Manager;

public interface ManagerDAO {
    Manager getByUsername(String username);
}
