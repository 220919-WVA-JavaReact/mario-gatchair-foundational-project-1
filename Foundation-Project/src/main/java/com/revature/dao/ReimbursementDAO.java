package com.revature.dao;

import com.revature.models.Reimbursement;

public interface ReimbursementDAO {
    Reimbursement getByTicketId(int ticketid);
}
