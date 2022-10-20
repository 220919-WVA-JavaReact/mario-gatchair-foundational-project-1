package com.revature.service;

import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDAOImplPostgres;
import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.models.Reimbursement;

import java.util.List;

public class ReimbursementServiceAPI {
    ReimbursementDAO rb = new ReimbursementDAOImplPostgres();
    public Reimbursement createRequest(Employee employee, double amount, String description, String type){
        return rb.createRequest(employee, amount, description, type);
    }

    public List<Reimbursement> viewAllRequest(Manager manager){
        List<Reimbursement> tickets = rb.viewAllReimbursement(manager);
        return tickets;
    }

    public List<Reimbursement> viewMyPendingRequest(Employee employee) {
        List<Reimbursement> tickets = rb.viewMyRequests(employee);
        return tickets;
    }
    public Reimbursement updateStatus(String status, int id, Manager manager){
        return rb.updateStatus(status, id, manager);
    }
    public List <Reimbursement> getByStatus(String status){
        return rb.getReimbursementByStatus(status);
    }
    public Reimbursement getByTicketId(int id){
        return rb.getReimbursementById(id, new Manager());
    }
}
