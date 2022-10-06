package com.revature;

import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.models.Reimbursement;
import com.revature.service.EmployeeService;
import com.revature.service.ManagerService;
import com.revature.service.ReimbursementService;

import java.util.Scanner;

public class App {
    public static EmployeeService es = new EmployeeService();
    public static ReimbursementService rs = new ReimbursementService();
    public static ManagerService ms = new ManagerService();
    public static void main(String[] args) {
        boolean running = true;
        Employee loggedinEmployee = null;
        Manager loggedinManager = null;
        Reimbursement request = null;


        while (running) {
            // the start-up
            System.out.println("Welcome to Revature's Employee Reimbursement Service");
            System.out.println("Please press 1 for Employee login, please press 2 for Managerial Login, press 3 for Reimbursement Options, or press 0 to exit");
            Scanner sc = new Scanner(System.in);
            String choice = sc.nextLine();
            while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("0")) {
                System.out.println("Invalid input");
                choice = sc.nextLine();
            }
            if (choice.equals("0")) {
                System.out.println("Ending program!");
                break;
            }
            if (choice.equals("1")) {
                System.out.println("Press 1 to Login. Press 2 to register, press 3 to view open reimbursement or press 4 to submit a new request");
                String subchoice = sc.nextLine();
                switch (subchoice) {
                    case "1":
                        loggedinEmployee = es.login();
                        break;
                    case "2":
                        loggedinEmployee = es.register();
                        break;
                    case "3":
                        rs.viewMyRequests(loggedinEmployee);
                        break;
                    case "4":
                        rs.createRequest(loggedinEmployee);
                        break;
                }
            } else if (choice.equals("2")) {
                System.out.println("Press 1 to Login. Press 2 to register, press 3 to view open reimbursement or press 4 to approve/deny");
                String subchoice = sc.nextLine();
                switch (subchoice) {
                    case "1":
                        loggedinManager = ms.login();
                        break;
                    case "2":
                        loggedinManager = ms.register();
                        break;
                    case "3":
                        rs.viewAllRequests(loggedinManager);
                        break;
                    case "4":
                        rs.updateStatus(loggedinManager);
                        break;
                }
            }
        }
    }
}
