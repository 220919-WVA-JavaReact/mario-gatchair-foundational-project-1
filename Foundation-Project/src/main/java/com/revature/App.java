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
        Employee loggedE = null;
        Manager loggedM = null;
        Reimbursement rqst = null;
        boolean programrunning = true;
        Scanner sc = new Scanner(System.in);
        // the start-up

        while (programrunning) {
            System.out.println("Welcome to Revature's Employee Reimbursement Service");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Press 1 for Employee Options, Press 2 for Manager Options or Press 0 to exit");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
            String choice = sc.nextLine();
            while (!choice.equals("1") && !choice.equals("2") && !choice.equals("0")) {
                System.out.println("Invalid input");
                choice = sc.nextLine();
            }
            if (choice.equals("1")) {
                System.out.println("Welcome Employee, what would you like to do?");
                System.out.println("1) Login");
                System.out.println("2) Register");
                System.out.println("*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*=*=*=*=");

                String response = sc.nextLine();
                switch (response) {
                    case "1":
                        loggedE = es.login();
                        while (loggedE != null) {
                            System.out.println("1) View Pending Reimbursement");
                            System.out.println("2) Create a new Reimbursement");
                            System.out.println("3) Logout");
                            System.out.println("*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
                            String employchoice = sc.nextLine();
                            switch (employchoice) {
                                case "1":
                                    rs.viewMyRequests(loggedE);
                                    break;
                                case "2":
                                    rs.createRequest(loggedE);
                                    break;
                                case "0":
                                    loggedE = null;
                                    System.out.println("Signing out!");
                                    break;
                                default:
                                    System.out.println("Sorry I don't understand that input. Try again.");
                                    break;
                            }
                        }
                        break;
                    case "2":
                        loggedE = es.register();
                        break;
                    default:
                        System.out.println("Sorry I don't understand that input. Try again.");
                        break;
                }
            } else if (choice.equals("2")) {
                System.out.println("Welcome Administrator, what would you like to do?");
                System.out.println("1) Login");
                System.out.println("2) Adjust Employee Status");
                System.out.println("*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*==*=*=*=*=");

                String response = sc.nextLine();
                switch (response) {
                    case "1":
                        loggedM = ms.login();
                        while (loggedM != null) {
                            System.out.println("1) View All Reimbursement");
                            System.out.println("2) Approve or Deny Reimbursements");
                            System.out.println("3) Logout");
                            System.out.println("*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
                            String manemploy = sc.nextLine();
                            switch (manemploy) {
                                case "1":
                                    rs.viewAllRequests(loggedM);
                                    break;
                                case "2":
                                    rs.updateStatus(loggedM);
                                    break;
                                default:
                                    System.out.println("Sorry I don't understand that input. Try again.");
                                case "0":
                                    loggedM = null;
                                    System.out.println("Signing out!");
                                    break;
                            }
                        }
                }
            } else if (choice.equals("0")) {
                System.out.println("Ending Program");
                programrunning = false;
            }

        }
    }
}