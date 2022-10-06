package com.revature.dao;

import com.revature.models.Employee;
import com.revature.models.Manager;
import com.revature.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerDAOImplPostgres implements ManagerDAO {
    @Override
    public Manager getByUsername(String username) {
        Manager manage = new Manager();

        try (Connection conn = ConnectionUtil.getConnection()){

            String sql = "SELECT * FROM Manager where username = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, username);

            ResultSet rs;

            if ((rs = stmt.executeQuery()) != null){
                rs.next();
                int id = rs.getInt("manager_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String receivedUsername = rs.getString("user_name");
                String password = rs.getString("password");

                manage = new Manager(id,name,email,username,password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manage;
    }

    @Override
    public Manager createManager(String name, String email, String username, String password) {
        Manager manager = new Manager();

        try (Connection conn = ConnectionUtil.getConnection()){
            String sql = "INSERT INTO Manager (name, email, user_name, password) VALUES (?,?,?,?) RETURNING *";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, name);
            stmt.setString(3, email);
            stmt.setString(4, username);
            stmt.setString(5, password);

            ResultSet rs;

            if ((rs = stmt.executeQuery()) != null) {

                rs.next();

                int id = rs.getInt("manager_id");
                String receivedname = rs.getString("name");
                String receivedEmail = rs.getString("email");
                String receivedUsername = rs.getString("user_name");
                String receivedPassword = rs.getString("password");

                manager = new Manager(id, receivedname, receivedEmail, receivedUsername, receivedPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Couldn't register user to the database");
        }
        return manager;
    }

    @Override
    public List<Manager> getManager() {
        Connection conn = ConnectionUtil.getConnection();

        List<Manager> managers = new ArrayList<>();

        try{
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM Manager";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                int id = rs.getInt("manager_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String username = rs.getString("user_name");
                String password = rs.getString("password");

                Manager manage = new Manager(id, name, email, username, password);
                managers.add(manage);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return managers;
    }
}
