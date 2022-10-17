package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.service.EmployeeServiceAPI;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthServlet extends HttpServlet {
    EmployeeServiceAPI employapi = new EmployeeServiceAPI();
    ObjectMapper obmap = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session;
        if (req.getParameter("action").equals("login")) {
            Employee employee = obmap.readValue(req.getInputStream(), Employee.class);
            String payload = employapi.login(employee.getUsername(), employee.getPassword());
            if (payload.equals("username")) {
                resp.setStatus(400);
                resp.getWriter().write("This username isn't registered, please try again.");
            } else if (payload.equals("password")) {
                resp.getWriter().write("Incorrect password.");
            } else {
                Employee employee1 = obmap.readValue(payload, Employee.class);
                session = req.getSession();
                session.setAttribute("auth-user", employee1);
                resp.setStatus(200);
                resp.getWriter().write(payload);
            }
        } else if (req.getParameter("action").equals("register")) {
            Employee employee = obmap.readValue(req.getInputStream(), Employee.class);
            Employee employee1 = employapi.register(employee.getFirst(), employee.getLast(), employee.getEmail(), employee.getUsername(), employee.getPassword());
            String respPayload = obmap.writeValueAsString(employee1);
            if (!respPayload.equals("null")) {
                session = req.getSession();
                session.setAttribute("auth-user", employee1);
                resp.setStatus(200);
                resp.getWriter().write(respPayload);
            } else {
                resp.setStatus(400);
                resp.getWriter().write("Username already in use!");
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
            resp.getWriter().write("You've been signed out!");
        }
    }
}
