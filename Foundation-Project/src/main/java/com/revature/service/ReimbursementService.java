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
        int amount = Integer.parseInt(sc.nextLine());

        System.out.println("Enter your reason: ");
        String description = sc.nextLine();

        Reimbursement request = rb.createRequest(employee, amount, description);
        return request;
    }

    public void viewMyRequests(Employee employee){
        List<Reimbursement> reimbursements = rb.viewMyRequests(employee);
        for(Reimbursement reimbursement : reimbursements) {
            System.out.println(reimbursement);
        }
    }

    public  void viewAllRequests(Manager manager){
        List<Reimbursement> reimbursements = rb.viewAllReimbursement(manager);
        for(Reimbursement reimbursement : reimbursements){
            System.out.println(reimbursement);
        }
    }
    public Reimbursement updateStatus(Manager manager){
        Reimbursement reimbursement = new Reimbursement();
        if (getReimbursementById(reimbursement.getHandledby(), manager).getManager().getManagerId() != manager.getManagerId()){
            String status = "Denied!";
            System.out.println("Reimbursement Ticket: " + reimbursement.getRequestid());
            System.out.println("Do you want to approve or deny?");
            System.out.println("1 to approve or 2 to deny");
            int choice = sc.nextInt();
            if(choice == 1){
                status = "Approved";
            }
            Reimbursement reimbursement1 = rb.updateStatus(status, reimbursement.getHandledby(), manager);
        } else {
            System.out.println("Not an authorized user.");
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
