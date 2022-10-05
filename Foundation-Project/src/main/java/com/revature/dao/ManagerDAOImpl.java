package com.revature.dao;

import com.revature.models.Manager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ManagerDAOImpl implements ManagerDAO{

    String line = "";
    String splitBy = ",";

    @Override
    public Manager getByUsername(String username){
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/managerdb.csv"));
            while ((line = br.readLine()) != null)
            {
                String[] info = line.split(splitBy);
                if (info[3].equals(username)) {
                    return new Manager(0, info[1], info[2], info[3], info[4]);
                }
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
