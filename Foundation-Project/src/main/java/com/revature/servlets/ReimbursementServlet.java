package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.models.Reimbursement;
import com.revature.service.ReimbursementServiceAPI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@WebServlet("/reimbursement")
public class ReimbursementServlet extends HttpServlet{
    ReimbursementServiceAPI rsapi = new ReimbursementServiceAPI();
    ObjectMapper obmap = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        HttpSession session = req.getSession(false);
        if (session != null) {
            Employee loggedE = (Employee) session.getAttribute("auth-user");
            if (req.getParameter("action").equals("view")) {
                List<Reimbursement> rslist = rsapi.viewMyPendingRequest(loggedE);
                System.out.println(rslist);
                for (Reimbursement reimbursement : rslist) {
                    if (req.getParameter("status").equals("Pending")) {
                        if (reimbursement.getStatus().equals("Pending")) {
                            resp.getWriter().write(reimbursement.getStatus());
                            resp.getWriter().write("Request ID: " + reimbursement.getRequestid() + " " + " | " + " ");
                            resp.getWriter().write("Amount:  " + reimbursement.getAmount() + " " + " | " + " ");
                            resp.getWriter().write("Description: " + reimbursement.getDescription() + " " + " | " + " ");
                            resp.getWriter().write("Type: " + reimbursement.getType() + " " + " | " + " \n");
                            resp.setStatus(200);
                        } else if (req.getParameter("status").equals("Approved")) {
                            if (reimbursement.getStatus().equals("Approved")) {
                                resp.getWriter().write(reimbursement.getStatus());
                                resp.getWriter().write("Request ID: " + reimbursement.getRequestid() + " " + " | " + " ");
                                resp.getWriter().write("Amount:  " + reimbursement.getAmount() + " " + " | " + " ");
                                resp.getWriter().write("Description: " + reimbursement.getDescription() + " " + " | " + " ");
                                resp.getWriter().write("Type: " + reimbursement.getType() + " " + " | " + "\n");
                                resp.setStatus(200);
                        } else if (req.getParameter("status").equals("Denied")) {
                                if (reimbursement.getStatus().equals("Denied")) {
                                    resp.getWriter().write(reimbursement.getStatus());
                                    resp.getWriter().write("Request ID: " + reimbursement.getRequestid() + " " + " | " + " ");
                                    resp.getWriter().write("Amount:  " + reimbursement.getAmount() + " " + " | " + " ");
                                    resp.getWriter().write("Description: " + reimbursement.getDescription() + " " + " | " + " ");
                                    resp.getWriter().write("Type: " + reimbursement.getType() + " " + " | " + "\n");
                                    resp.setStatus(200);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        HttpSession session = req.getSession(false);
        if (session != null) {
            Employee loggedE = (Employee) session.getAttribute("auth-user");
            Reimbursement reimbursement = obmap.readValue(req.getInputStream(), Reimbursement.class);
            if (reimbursement.getAmount() <= 0 || String.valueOf(reimbursement.getAmount()).equals("")) {
                resp.getWriter().write("You must have enter in a valid expense amount");
                resp.setStatus(403);
            } else if (reimbursement.getDescription().trim().equals("")) {
                resp.getWriter().write("You must enter a valid explanation and cannot be blanked");
                resp.setStatus(403);
            } else if (reimbursement.getType().trim().equals("")) {
                resp.getWriter().write("Type can not be empty.");
                resp.setStatus(403);
            } else if (!reimbursement.getType().equals("Travel") && !reimbursement.getType().equals("Lodging") && !reimbursement.getType().equals("Food") && !reimbursement.getType().equals("Other")){
            resp.getWriter().write("Type must be either 'Travel', 'Lodging', 'Food' or 'Other");
            resp.setStatus(403);
            } else {
                Reimbursement payload = rsapi.createRequest(loggedE, reimbursement.getAmount(), reimbursement.getDescription(), reimbursement.getType());
                resp.getWriter().write(obmap.writeValueAsString(payload));
                resp.setStatus(201);
            }
        }
    }

        @Override
        protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setContentType("application/json");
            HttpSession session = req.getSession(false);
            if (session != null) {
                Manager loggedM = (Manager) session.getAttribute("auth-user");
                Reimbursement reimbursement = obmap.readValue(req.getInputStream(), Reimbursement.class);
                if (req.getParameter("action").equals("approve")){
                   if (reimbursement.getStatus().equals("Pending")){
                       String respPayload = obmap.writeValueAsString(reimbursement);
                       String request = String.valueOf(rsapi.updateStatus("Approved",reimbursement.getRequestid(),loggedM));
                       resp.getWriter().write("Ticket Approved");
                       resp.setStatus(200);
                   } else {
                       resp.setStatus(400);
                       resp.getWriter().write("This ticket has been previously resolved.");
                   }
                } else if (req.getParameter("action").equals("deny")){
                    if (reimbursement.getStatus().equals("Pending")){
                        String respPayload = obmap.writeValueAsString(reimbursement);
                        String request = String.valueOf(rsapi.updateStatus("Denied", reimbursement.getRequestid(), loggedM));
                        resp.getWriter().write("Ticket Denied");
                        resp.setStatus(200);
                    } else {
                        resp.setStatus(400);
                        resp.getWriter().write("This request has been resolved previously.");
                    }
                }
            } else {
                resp.setStatus(402);
            }
        }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
