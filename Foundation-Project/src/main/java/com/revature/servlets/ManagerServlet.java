package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Manager;
import com.revature.models.Reimbursement;
import com.revature.service.ManagerServicesAPI;
import com.revature.service.ReimbursementServiceAPI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/manager")
public class ManagerServlet extends HttpServlet {
    ManagerServicesAPI manapi = new ManagerServicesAPI();

    ReimbursementServiceAPI rsapi = new ReimbursementServiceAPI();

    ObjectMapper obmap = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        HttpSession session = req.getSession(false);
        if (session != null) {
            Manager manager = (Manager) session.getAttribute("auth-user");
            if (req.getParameter("action").equals("view")) {
                List<Reimbursement> allRsList = rsapi.viewAllRequest(manager);
                for (Reimbursement reimbursement : allRsList) {
                    if (req.getParameter("status").equals("Pending")) {
                        if (reimbursement.getStatus().equals("Pending")) {
                            resp.getWriter().write(reimbursement.getStatus());
                            resp.getWriter().write("Request ID: " + reimbursement.getRequestid() + " " + " | " + " ");
                            resp.getWriter().write("Amount:  " + reimbursement.getAmount() + " " + " | " + " ");
                            resp.getWriter().write("Description: " + reimbursement.getDescription() + " " + " | " + " ");
                            resp.getWriter().write("Type: " + reimbursement.getType() + " " + " | " + " \n");
                            resp.setStatus(200);
                        }
                    } else if (req.getParameter("status").equals("Approved")) {
                        if (reimbursement.getStatus().equals("Approved")) {
                            resp.getWriter().write(reimbursement.getStatus());
                            resp.getWriter().write("Request ID: " + reimbursement.getRequestid() + " " + " | " + " ");
                            resp.getWriter().write("Amount:  " + reimbursement.getAmount() + " " + " | " + " ");
                            resp.getWriter().write("Description: " + reimbursement.getDescription() + " " + " | " + " ");
                            resp.getWriter().write("Type: " + reimbursement.getType() + " " + " | " + "\n");
                            resp.setStatus(200);
                        }
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

        } else {
            resp.setStatus(401);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
