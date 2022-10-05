package com.revature.models;

import java.util.Objects;

public class Reimbursement {

    private int ticketid;

    private int employeeid;

    private double amount;

    private String handledby;

    private String description;

    public Reimbursement(int ticketid, int employeeid, double amount, String handledby, String description) {
        this.ticketid = ticketid;
        this.employeeid = employeeid;
        this.amount = amount;
        this.handledby = handledby;
        this.description = description;
    }

    public Reimbursement(int ticketid, int employeeid, double amount, String handledby) {
        this.ticketid = ticketid;
        this.employeeid = employeeid;
        this.amount = amount;
        this.handledby = handledby;
    }

    public int getTicketid() {
        return ticketid;
    }

    public void setTicketid(int ticketid) {
        this.ticketid = ticketid;
    }

    public int getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getHandledby() {
        return handledby;
    }

    public void setHandledby(String handledby) {
        this.handledby = handledby;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "ticketid=" + ticketid +
                ", employeeid=" + employeeid +
                ", amount=" + amount +
                ", handledby='" + handledby + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement that = (Reimbursement) o;
        return ticketid == that.ticketid && employeeid == that.employeeid && Double.compare(that.amount, amount) == 0 && Objects.equals(handledby, that.handledby) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketid, employeeid, amount, handledby, description);
    }
}
