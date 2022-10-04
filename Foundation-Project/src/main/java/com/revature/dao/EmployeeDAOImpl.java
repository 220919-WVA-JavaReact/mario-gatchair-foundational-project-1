package com.revature.dao;

import com.revature.models.Employee;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EmployeeDAOImpl implements EmployeeDAO {
    String line = "";
    String splitBy = ",";
    @Override
    public Employee getByUsername(String username){

        try{
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/employeedb.csv"));
            while ((line = br.readLine()) != null)
            {
                String [] info = line.split(splitBy);
                if (info[4].equals(username)){
                    return new Employee(0, info[1], info[2], info[3], info[4], info[5]);
                }
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
