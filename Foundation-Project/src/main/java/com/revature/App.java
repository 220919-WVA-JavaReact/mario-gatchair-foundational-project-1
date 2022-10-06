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
        Employee loggedinEmployee = null;
        Manager loggedinManager = null;
        Reimbursement request = null;
        // the start-up
        System.out.println("Welcome to Revature's Employee Reimbursement Service");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Press 1 for Employee login, Press 2 for Employee Registration, Press 3 for Manager login or Press 4 for Manager Registration");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();
            while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4")) {
                System.out.println("Invalid input");
                choice = sc.nextLine();
            }
            switch (choice) {
                case "1":
                    loggedinEmployee = es.login();
                    break;
                case "2":
                    loggedinEmployee = es.register();
                    break;
                case "3":
                    loggedinManager = ms.login();
                    break;
                case "4":
                    loggedinManager = ms.register();
                    break;
            }
        while (loggedinEmployee != null || loggedinManager != null) {
            if (loggedinEmployee != null) {
                System.out.println("Press 1 to view Request, Press 2 to Create a new a Request");
                String subchoice = sc.nextLine();
                switch (subchoice) {
                    case "1":
                        rs.viewMyRequests(loggedinEmployee);
                        break;
                    case "2":
                        rs.createRequest(loggedinEmployee);
                        break;
                    case "3":
                        loggedinEmployee = null;
                        break;
                    default:
                        System.out.println("Invalid input");
                        break;
                }
            } else if (loggedinManager != null) {
                System.out.println("Press 1 to View Pending Requests, Press 2 to approve or deny pending requests");
                String subchoice = sc.nextLine();
                switch (subchoice) {
                    case "1":
                        rs.viewAllRequests(loggedinManager);
                        break;
                    case "2":
                        rs.updateStatus(loggedinManager);
                        break;
                    case "3":
                        loggedinManager = null;
                        break;
                    default:
                        System.out.println("Invalid input");
                        break;
                }
            }
        }
    }
}
