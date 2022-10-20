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
            if (req.getParameter("type").equals("employee")) {
                List<Reimbursement> rslist = rsapi.viewMyPendingRequest(loggedE);
                if (rslist.size() < 1) {
                    resp.getWriter().write("You no submitted requests");
                } else {
                    String collection = obmap.writeValueAsString(rslist);
                    resp.getWriter().write(collection);
                }
            } else {
                if (req.getParameter("status").equals("Pending")) {
                    List<Reimbursement> reimbursements = rsapi.getByStatus("Pending");
                    String payload = obmap.writeValueAsString(reimbursements);
                    resp.getWriter().write(payload);
                    resp.setStatus(200);
                } else if (req.getParameter("status").equals("Approved")) {
                    List<Reimbursement> reimbursements = rsapi.getByStatus("Approved");
                    String payload = obmap.writeValueAsString(reimbursements);
                    resp.getWriter().write(payload);
                    resp.setStatus(200);
                } else if (req.getParameter("status").equals("Denied")) {
                    List<Reimbursement> reimbursements = rsapi.getByStatus("Denied");
                    String payload = obmap.writeValueAsString(reimbursements);
                    resp.getWriter().write(payload);
                    resp.setStatus(200);
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
                resp.setStatus(400);
            } else if (reimbursement.getDescription().equals("")) {
                resp.getWriter().write("You must enter a valid explanation and cannot be blanked");
                resp.setStatus(400);
            } else if (reimbursement.getType().equals("")) {
                resp.getWriter().write("Type can not be empty.");
                resp.setStatus(400);
            } else if (!reimbursement.getType().equals("Travel") && !reimbursement.getType().equals("Lodging") && !reimbursement.getType().equals("Food") && !reimbursement.getType().equals("Other")){
            resp.getWriter().write("Type must be either 'Travel', 'Lodging', 'Food' or 'Other");
            resp.setStatus(400);
            } else {
                Reimbursement payload = rsapi.createRequest(loggedE, reimbursement.getAmount(), reimbursement.getDescription(), reimbursement.getType());
                resp.getWriter().write(obmap.writeValueAsString(payload));
                resp.setStatus(201);
            }
        }
    }



    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
