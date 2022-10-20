package com.revature.dao;

import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.models.Reimbursement;
import com.revature.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDAOImplPostgres implements ReimbursementDAO{
    @Override
    public Reimbursement createRequest(Employee employee, double amount, String description, String type) {
        Reimbursement reimbursement = new Reimbursement();
        try (Connection conn = ConnectionUtil.getConnection()){
            String sql = "INSERT INTO Reimbursement (employee_id, amount, status, description, type) VALUES (?,?,?,?,?) RETURNING *";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, employee.getEmployeeId());
            stmt.setDouble(2, amount);
            stmt.setString(3, "Pending");
            stmt.setString(4, description);
            stmt.setString(5, type);
            ResultSet rs;
            if ((rs = stmt.executeQuery()) != null){
                rs.next();
                int id = rs.getInt("ID");
                double requestedamount = rs.getDouble("amount");
                String status = rs.getString("status");
                String reason = rs.getString("description");
                String category = rs.getString("type");
                int handledby = rs.getInt("handled_by");
                return new Reimbursement(id, requestedamount, status, reason, category, employee, handledby);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Reimbursement> viewAllReimbursement(Manager manager) {
        List<Reimbursement> reimbursements = new ArrayList<>();
        Employee employee = new Employee();
        try (Connection conn = ConnectionUtil.getConnection()){
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM Reimbursement NATURAL JOIN Employee";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("ID");
                double amount = rs.getDouble("amount");
                String status = rs.getString("status");
                String description = rs.getString("description");
                String type = rs.getString("type");
                int handledby = rs.getInt("handled_by");
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFirst(rs.getString("first"));
                employee.setLast(rs.getString("last"));
                employee.setEmail(rs.getString("email"));
                employee.setUsername(rs.getString("username"));
                employee.setPassword(rs.getString("password"));
                Reimbursement reimburse = new Reimbursement(id, amount, status, description, type, employee , handledby);
                reimbursements.add(reimburse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursements;
    }

    @Override
    public List<Reimbursement> viewMyRequests(Employee employee) {
        List<Reimbursement> reimbursement = new ArrayList<>();

        try (Connection conn = ConnectionUtil.getConnection()){

            String sql = "SELECT * FROM Reimbursement WHERE employee_id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, employee.getEmployeeId());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {



                int id = rs.getInt("ID");
                int employee_id = rs.getInt("employee_id");
                double amount = rs.getDouble("amount");
                String status = rs.getString("status");
                String description = rs.getString("description");
                String type = rs.getString("type");
                int handled_by = rs.getInt("handled_by");
                Reimbursement request = new Reimbursement(id, amount, status, description, type, employee, handled_by);
                reimbursement.add(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            }
        return reimbursement;
        }

    @Override
    public List<Reimbursement> viewRequestsByType(String type, Employee employee) {
        List<Reimbursement> reimbursement = new ArrayList<>();

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "SELECT * FROM Reimbursement WHERE employee_id = ? AND status = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, employee.getEmployeeId());
            stmt.setString(2, type);
            ResultSet rs;
            if ((rs = stmt.executeQuery()) != null) {
                rs.next();

                int id = rs.getInt("ID");
                double amount = rs.getDouble("amount");
                String status = rs.getString("status");
                String description = rs.getString("description");
                String category = rs.getString("type");
                int handled_by = rs.getInt("handled_by");
                Reimbursement request = new Reimbursement(id, amount, status, description, type, employee, handled_by);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursement;
    }

    @Override
    public List<Reimbursement> getReimbursementByType(Employee employee, String type) {
        return null;
    }

    @Override
    public List<Reimbursement> getReimbursementByStatus(String status) {
        return null;
    }

    @Override
    public Reimbursement updateStatus(String status, int id, Manager manager) {
        Reimbursement reimbursement = new Reimbursement();
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "UPDATE Reimbursement r SET status = ?, handled_by = ? WHERE r.ID = ? RETURNING *";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            stmt.setInt(2, manager.getManagerId());
            stmt.setInt(3, id);
            ResultSet rs;
            if ((rs = stmt.executeQuery()) != null){
                while (rs.next()) {
                    int r_id = rs.getInt("ID");
                    System.out.println(r_id);
                    double amount = rs.getDouble("amount");
                    String approvalstatus = rs.getString("status");
                    String description = rs.getString("description");
                    String type = rs.getString("type");
                    int handled_by = rs.getInt("handled_by");
                    reimbursement = new Reimbursement(r_id, amount, approvalstatus, description, type, handled_by, manager);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursement;
    }

    @Override
    public Reimbursement getReimbursementById(int id, Manager manager){
        Reimbursement reimbursement = new Reimbursement();
        Employee emp = new Employee();
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM Reimbursement r NATURAL JOIN Employee e WHERE r.ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs;
            if ((rs = stmt.executeQuery()) != null) {
                rs.next();
                int r_id = rs.getInt("ID");
                double amount = rs.getDouble("amount");
                String status = rs.getString("status");
                String description = rs.getString("description");
                String type = rs.getString("type");
                int handled_by = rs.getInt("handled_by");
                emp.setEmployeeId(rs.getInt("employee_id"));
                emp.setFirst(rs.getString("first"));
                emp.setLast(rs.getString("last"));
                emp.setEmail(rs.getString("email"));
                emp.setUsername(rs.getString("username"));
                Reimbursement request = new Reimbursement(r_id, amount, status, description, type, handled_by, emp, manager);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursement;
    }
}
