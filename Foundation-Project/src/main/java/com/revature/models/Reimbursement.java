package com.revature.models;

import java.util.Objects;

public class Reimbursement {

    private int ticketid;

    private double amount;

    private String status;

    private String description;

    public Reimbursement(int ticketid, double amount, String status, String description) {
        this.ticketid = ticketid;
        this.amount = amount;
        this.status = status;
        this.description = description;
    }

    public Reimbursement() {
    }

    public int getTicketid() {
        return ticketid;
    }

    public void setTicketid(int ticketid) {
        this.ticketid = ticketid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement that = (Reimbursement) o;
        return ticketid == that.ticketid && Double.compare(that.amount, amount) == 0 && Objects.equals(status, that.status) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketid, amount, status, description);
    }
}
