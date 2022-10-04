package com.revature.dao;

import com.revature.models.Employee;

public interface EmployeeDAO {
    Employee getByUsername(String username);
}
