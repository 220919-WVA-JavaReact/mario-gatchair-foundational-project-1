package com.revature.service;

import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDAOImplPostgres;
import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.models.Reimbursement;

import java.util.List;
import java.util.Scanner;

public class ReimbursementService {
    ReimbursementDAO rb = new ReimbursementDAOImplPostgres();
    Scanner sc = new Scanner(System.in);

    public Reimbursement createRequest(Employee employee){
        // pass in currently logged in user, if employee set manager to null, vice versa if manager set employee to null

        System.out.println("Enter your amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();
        System.out.println("Enter your reason: ");
        String description = sc.nextLine();

        Reimbursement request = rb.createRequest(employee, amount, description);
        return request;
    }

    public void viewMyRequests(Employee employee){
        List<Reimbursement> reimbursements = rb.viewMyRequests(employee);
        if (reimbursements.size() > 0) {
            for(Reimbursement reimbursement : reimbursements) {
                System.out.println(reimbursement);
            }
        } else {
            System.out.println("You have no requests");
        }
    }

    public void viewAllRequests(Manager manager){
        List<Reimbursement> reimbursements = rb.viewAllReimbursement(manager);
        for(Reimbursement reimbursement : reimbursements){
            System.out.println(reimbursement);
        }
    }
    public Reimbursement updateStatus(Manager manager){
        List<Reimbursement> reimbursements = rb.viewAllReimbursement(manager);
        System.out.println("The following tickets need approval.");
        for(Reimbursement reimbursement : reimbursements){
            if (reimbursement.getStatus().equals("Pending")){
                System.out.println(reimbursement.getStatus() + " * Ticket Id: " + reimbursement.getRequestid() + " || Amount: " + reimbursement.getAmount() + " || Reason:" + reimbursement.getDescription());
            }
        }
        System.out.println("Enter ticket Id that you wish to update");
        int ticketid = sc.nextInt();
        Reimbursement reimbursement = rb.getReimbursementById(ticketid,manager);
        System.out.println("Do you want to approve or deny?");
        System.out.println("1 to approve or 2 to deny");
        int choice = sc.nextInt();
        String status = "Denied";
        if(choice == 1){
            status = "Approved";
        }
        Reimbursement reimbursement1 = rb.updateStatus(status, ticketid, manager);
        if (!reimbursement1.getStatus().equals(reimbursement.getStatus())){
            System.out.println("Request has been updated");
        } else {
            System.out.println("Request update has failed");
        }
        return null;
    }

    public Reimbursement getReimbursementById(int id, Manager manager){
        Reimbursement reimbursement = rb.getReimbursementById(id, manager);
        System.out.println(reimbursement.getStatus() + "Reimbursement request sent by:" + reimbursement.getEmployee().getUsername());
        System.out.println("Amount requested: $" + reimbursement.getAmount() + "Reason given: " + reimbursement.getDescription());
        return reimbursement;
    }

}
