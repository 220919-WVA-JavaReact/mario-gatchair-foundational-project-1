package com.revature.dao;

import com.revature.models.Reimbursement;
import com.revature.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDAOImplPostgres implements ReimbursementDAO{
    @Override
    public Reimbursement createRequest(int employeeid, int amount, String handledby, String description) {
        return null;
    }

    @Override
    public List<Reimbursement> getAllReimbursement() {

        Connection conn = ConnectionUtil.getConnection();

        List<Reimbursement> reimbursements = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            // My Logic should go inside here
            String sql = "SELECT * FROM teachers";

            // Run the statement
            ResultSet rs = stmt.executeQuery(sql);


            while (rs.next()) {
                int id = rs.getInt("id");
                int employee_id = rs.getInt("employee_id");
                int amount = rs.getInt("Amount");
                String status = rs.getString("status");
                int handled_by = rs.getInt("handled_by");
                String description = rs.getString("description");


                Reimbursement reimburs = new Reimbursement(id, employee_id, amount, status, handled_by, description);

                reimbursements.add(reimburs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursements;
    }

    @Override
    public List<Reimbursement> getReimbursementByEmployeeId(int employeeid) {
        Reimbursement reimburse = new Reimbursement();

        try (Connection conn = ConnectionUtil.getConnection()){

            String sql = "SELECT * from reimbursement where Employee ID = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, String.valueOf(employeeid));

            ResultSet rs;

            if ((rs = stmt.executeQuery()) != null) {

                rs.next();

                int id = rs.getInt("id");
                int employee_id = rs.getInt("employee_id");
                int amount = rs.getInt("Amount");
                String status = rs.getString("status");
                int handled_by = rs.getInt("handled_by");
                String description = rs.getString("description");

                reimburse = new Reimbursement(id, employee_id, amount, status, handled_by, description);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            }
        return reimburse;
        }

    @Override
    public boolean updateStatus(String status) {
        return false;
    }
}
