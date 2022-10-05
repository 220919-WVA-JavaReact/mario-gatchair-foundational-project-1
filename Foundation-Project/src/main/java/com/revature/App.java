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

    public static ReimbursementService re = new ReimbursementService();

    public static ManagerService mn = new ManagerService();

    public static void main(String[] args) {
        boolean running = true;

        Employee loggedinEmployee = null;

        Manager loggedinManager = null;



        while (running) {
            // the start-up
            System.out.println("Welcome to Revature's Employee Reimbursement Service");
            System.out.println("Please press 1 for Employee login, please press 2 for Employee registration, press 3 to check status, press 4 for Managerial login, or press 0 to exit");
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

                loggedinEmployee = es.login();

            } else if (choice.equals("2")) {

                loggedinEmployee = es.register();

            } else if (choice.equals(("3"))) {



            } else if (choice.equals("4")) {

                loggedinManager = mn.login();
            }
            if (loggedinEmployee != null){
                }
            }
    }
}
