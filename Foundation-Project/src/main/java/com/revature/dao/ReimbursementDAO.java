package com.revature.dao;

import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.models.Reimbursement;

import java.io.ObjectInputFilter;
import java.util.List;

public interface ReimbursementDAO {
    Reimbursement createRequest(Employee employee, double amount, String description);

    List<Reimbursement> viewAllReimbursement(Manager manager);

    List<Reimbursement> viewMyRequests(Employee employee);

    Reimbursement getReimbursementById(int id, Manager manager);

    Reimbursement updateStatus(String status, int id, Manager manager);
}
