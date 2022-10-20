package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.service.EmployeeServiceAPI;
import com.revature.service.ManagerServicesAPI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    EmployeeServiceAPI employapi = new EmployeeServiceAPI();
    ManagerServicesAPI manageapi = new ManagerServicesAPI();
    ObjectMapper obmap = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session;
        if (req.getParameter("action").equals("login")){
            if (req.getParameter("type").equals("employee")) {
                Employee employee = obmap.readValue(req.getInputStream(), Employee.class);
                Employee employ = employapi.login(employee.getUsername(), employee.getPassword());
                String payload = obmap.writeValueAsString(employ);
                if (payload.equals("null")) {
                    resp.setStatus(401);
                    resp.getWriter().write("Invalid credentials!");
                } else {
                    session = req.getSession();
                    session.setAttribute("auth-user", employ);
                    resp.setStatus(200);
                    resp.getWriter().write("Welcome " + employ.getFirst() + "! What do you want to do today?");
                }
            } else if(req.getParameter("type").equals("manager")) {
                Manager manager = obmap.readValue(req.getInputStream(), Manager.class);
                Manager manage = manageapi.login(manager.getUsername(), manager.getPassword());
                String payload = obmap.writeValueAsString(manage);
                if (payload.equals("null")){
                    resp.setStatus(401);
                    resp.getWriter().write("Invalid credentials!");
                } else {
                    session = req.getSession();
                    session.setAttribute("auth-user", manage);
                    resp.setStatus(200);
                    resp.getWriter().write("Welcome back " + manage.getFirst() + "! What do you want to do today?");
                }
            }
        } else if (req.getParameter("action").equals("register")) {
            if (req.getParameter("type").equals("employee")) {
                Employee employee = obmap.readValue(req.getInputStream(), Employee.class);
                Employee employee1 = employapi.register(employee.getFirst(), employee.getLast(), employee.getEmail(), employee.getUsername(), employee.getPassword());
                String respPayload = obmap.writeValueAsString(employee1);
                if (!respPayload.equals("null")) {
                    session = req.getSession();
                    session.setAttribute("auth-user", employee1);
                    resp.setStatus(201);
                    resp.getWriter().write("Welcome and thank you for signing up!");
                } else {
                    resp.setStatus(401);
                    resp.getWriter().write("Username already in use!");
                }
            } else if (req.getParameter("action").equals("register")) {
                if (req.getParameter("type").equals("manager")) {
                    Manager manager = obmap.readValue(req.getInputStream(), Manager.class);
                    Manager manage1 = manageapi.register(manager.getFirst(), manager.getLast(), manager.getEmail(), manager.getUsername(), manager.getPassword());
                    String respPayloadM = obmap.writeValueAsString(manage1);
                    if (!respPayloadM.equals("null")) {
                        session = req.getSession();
                        session.setAttribute("auth-user", manage1);
                        resp.setStatus(201);
                        resp.getWriter().write("Thanks for joining Management!");
                    } else {
                        resp.setStatus(401);
                        resp.getWriter().write("User already exists");
                    }
                }
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
            resp.setStatus(200);
            resp.getWriter().write("You've been signed out!");
        }
    }
}
