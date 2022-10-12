package com.revature.dao;

import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImplPostgres implements EmployeeDAO{
    @Override
    public Employee getByUsername(String username) {

        Employee employ = new Employee();

        try (Connection conn = ConnectionUtil.getConnection()){

            String sql = "SELECT * FROM Employee where user_name = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, username);

            ResultSet rs;

            if ((rs = stmt.executeQuery()) != null){
                rs.next();
                int id = rs.getInt("employee_id");
                String first = rs.getString("first");
                String last = rs.getString("last");
                String email = rs.getString("email");
                String receivedUsername = rs.getString("user_name");
                String password = rs.getString("password");

                employ = new Employee(id,first,last,email,receivedUsername,password);
            }
        } catch (SQLException e) {
            System.out.println("Invalid info please check your credentials or register");
            System.out.println(e);//printStackTrace();
            return null;
        }
        return employ;
    }

    @Override
    public Employee createEmployee(String first, String last, String email, String username, String password) {

        Employee employee = new Employee();

        try (Connection conn = ConnectionUtil.getConnection()){
            String sql = "INSERT INTO Employee (first, last, email, user_name, password) VALUES (?,?,?,?,?) RETURNING *";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, first);
            stmt.setString(2, last);
            stmt.setString(3, email);
            stmt.setString(4, username);
            stmt.setString(5, password);

            ResultSet rs;

            if ((rs = stmt.executeQuery()) != null) {

                rs.next();

                int id = rs.getInt("employee_id");
                String receivedFirst = rs.getString("first");
                String receivedLast = rs.getString("last");
                String receivedEmail = rs.getString("email");
                String receivedUsername = rs.getString("user_name");
                String receivedPassword = rs.getString("password");

                employee = new Employee(id, receivedFirst, receivedLast, receivedEmail, receivedUsername, receivedPassword);
            }
        } catch (SQLException e) {
            System.out.println("User already exists couldn't register user to the database.");
            System.out.println("Please register with valid credentials.");
        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        Connection conn = ConnectionUtil.getConnection();

        List<Employee> employees = new ArrayList<>();

        try{
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM employee";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                int id = rs.getInt("employee_id");
                String first = rs.getString("first");
                String last = rs.getString("last");
                String email = rs.getString("email");
                String username = rs.getString("user_name");
                String password = rs.getString("password");

                Employee employ = new Employee(id, first, last, email, username, password);
                employees.add(employ);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public Employee promoteEmployee(int ind, String first, String last, String username, String password) {
        Employee employee = new Employee();
        try (Connection conn = ConnectionUtil.getConnection()){
            String sql = "INSERT INTO manager SELECT * FROM employee WHERE user_name = ? RETURNING *";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                int id = rs.getInt("employee_id");
                String empfirst = rs.getString("first");
                String emplast = rs.getString("last");
                String email = rs.getString("email");
                String empusername = rs.getString("user_name");
                String empassword = rs.getString("password");
                Employee employee1 = new Manager(id, empfirst, emplast, email, empusername, empassword);
            }

        } catch (SQLException e) {
           e.printStackTrace();
        }
        return employee1;
    }
}
