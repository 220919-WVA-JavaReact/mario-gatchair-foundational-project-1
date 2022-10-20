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
        resp.setContentType("application");
        HttpSession session = req.getSession(false);
        if (session != null) {
            Manager manager = (Manager) session.getAttribute("auth-user");
            if (req.getParameter("action").equals("view")){
                List<Reimbursement> allrslist = rsapi.viewAllRequest(manager);
                if (allrslist.size() < 1 ){
                    resp.getWriter().write("There are no requests at this moment");
                } else {
                    String collection = obmap.writeValueAsString(allrslist);
                    resp.getWriter().write(collection);
                }
            }else {
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
    }

    //    @Override
    //    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //        resp.getContentType("application/json");
    //        HttpSession session = req.getSession(false);
    //        if (session != null) {
    //            Manager loggedM = (Manager) session.getAttribute("auth-user")l;
    //            Reimbursement reimbursement = obmap.readValue(req.getInputStream(), Reimbursement.class);
    //            if (reimbursement)
    //        }
    //    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
