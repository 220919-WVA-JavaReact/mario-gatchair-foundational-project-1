package com.revature.dao;

import com.revature.models.Reimbursement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReimbursementDAOImpl implements ReimbursementDAO{
    String line = "";
    String splitBy = ",";
    @Override
    public Reimbursement getByTicketId(int ticketid){
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/Foundation-project-database.sql"));
            while ((line = br.readLine()) != null)
            {
                String[] info = line.split(splitBy);
                if (info[1].equals(ticketid)){
                    return new Reimbursement(0, 2, info[2], info[3]);
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
