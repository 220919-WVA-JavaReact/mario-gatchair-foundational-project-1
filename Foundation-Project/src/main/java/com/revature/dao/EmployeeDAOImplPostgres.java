package com.revature.dao;

import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImplPostgres implements EmployeeDAO{
    @Override
    public Employee getByUsername(String username) throws NullPointerException{
        Employee employ = new Employee();
        try (Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM employee WHERE username = ?";
            System.out.println(username);
//            assert conn != null;
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs;
            if ((rs = stmt.executeQuery()) != null){
                if (rs.next()) {
                    int id = rs.getInt("ID");
                    String first = rs.getString("first name");
                    String last = rs.getString("last name");
                    String email = rs.getString("email");
                    String receivedUsername = rs.getString("username");
                    String password = rs.getString("password");
                    employ = new Employee(id, first, last, email, receivedUsername, password);
                }
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
            String sql = "INSERT INTO Employee ('first name', 'last name', email, username, password) VALUES (?,?,?,?,?) RETURNING *";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, first);
            stmt.setString(2, last);
            stmt.setString(3, email);
            stmt.setString(4, username);
            stmt.setString(5, password);

            ResultSet rs;

            if ((rs = stmt.executeQuery()) != null) {

                rs.next();

                int id = rs.getInt("ID");
                String receivedFirst = rs.getString("first name");
                String receivedLast = rs.getString("last name");
                String receivedEmail = rs.getString("email");
                String receivedUsername = rs.getString("username");
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
    public List<Employee> getAllEmployees(Employee loggedE) {
        Connection conn = ConnectionUtil.getConnection();

        List<Employee> employees = new ArrayList<>();

        try{
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM employee";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                int id = rs.getInt("ID");
                String first = rs.getString("first name");
                String last = rs.getString("last name");
                String email = rs.getString("email");
                String username = rs.getString("username");
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
    public boolean existingEmployee(String username) {
        try (Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM employee WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            if ((stmt.executeQuery()) != null) {
                return true;
            }
        } catch (SQLException e){
            return false;
        }
        return false;
    }

    /*@Override
    public Employee promoteEmployee(int ind, String first, String last, String username, String password) {
        Employee employee = new Employee();
        try (Connection conn = ConnectionUtil.getConnection()){
            String sql = "INSERT INTO manager SELECT * FROM employee WHERE username = ? RETURNING *";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                int id = rs.getInt("ID");
                String empfirst = rs.getString("first name");
                String emplast = rs.getString("last name");
                String email = rs.getString("email");
                String empusername = rs.getString("username");
                String empassword = rs.getString("password");
                Employee employee1 = new Manager(id, empfirst, emplast, email, empusername, empassword);
            }

        } catch (SQLException e) {
           e.printStackTrace();
        }
        return employee1;
    }*/
}
