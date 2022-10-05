package com.revature.service;

import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDAOImpl;
import com.revature.models.Reimbursement;

import java.util.Scanner;

public class ReimbursementService {

    Scanner sc = new Scanner(System.in);

    ReimbursementDAO reim = new ReimbursementDAOImpl();


    public void createReimbursement(){
        System.out.println("Enter your Employee ID: ");
        int employeeid = Integer.parseInt(sc.nextLine());

        System.out.println("Enter your amount: ");
        int amount = Integer.parseInt(sc.nextLine());

        System.out.println("Enter your reason: ");
        String description = sc.nextLine();

        reim.createRequest(employeeid, amount, "Pending", description);
    }
    public void approveReimbursement(){

    }
}
