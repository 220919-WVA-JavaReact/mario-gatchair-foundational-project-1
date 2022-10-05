package com.revature.dao;

import com.revature.models.Reimbursement;

import java.io.*;
import java.util.List;

public class ReimbursementDAOImpl implements ReimbursementDAO {

    @Override
    public Reimbursement createRequest(int employeeid, int amount, String handledby , String description) {
        System.out.println("Request created");
        return null;
    }

    @Override
    public List<Reimbursement> getAllReimbursement() {
        System.out.println("All tickets pulled");
        return null;
    }

    @Override
    public List<Reimbursement> getReimbursementByEmployeeId(int employeeid) {
        System.out.println("Found Your request");
        return null;
    }

    @Override
    public boolean updateStatus(ObjectInputFilter.Status status) {
        System.out.println("Status updated");
        return false;
    }
}
