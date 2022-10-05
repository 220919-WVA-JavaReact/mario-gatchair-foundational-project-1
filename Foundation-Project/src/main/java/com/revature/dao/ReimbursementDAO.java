package com.revature.dao;

import com.revature.models.Reimbursement;

import java.io.ObjectInputFilter;
import java.util.List;

public interface ReimbursementDAO {
    Reimbursement createRequest(int employeeid, int amount, String handledby, String description);

    List<Reimbursement> getAllReimbursement();

    List<Reimbursement> getReimbursementByEmployeeId(int employeeid);

    boolean updateStatus(String status);
}
