package com.revature.models;

import java.util.Objects;

public class Reimbursement {

    private int requestid;

    private double amount;

    private  String status = "Pending";

    private String description;

    private String type;

    private int handledby;

    private Employee employee;

    private Manager manager;

    public Reimbursement() {
    }

    public Reimbursement(int requestid, double amount, String status, String description, String type, Employee employee, int handledby) {
        this.requestid = requestid;
        this.amount = amount;
        this.status = status;
        this.description = description;
        this.type = type;
        this.employee = employee;
        this.handledby = handledby;
    }

    public Reimbursement(int requestid, double amount, String status, String description, String type, int handledby, Manager manager) {
        this.requestid = requestid;
        this.amount = amount;
        this.status = status;
        this.description = description;
        this.type =  type;
        this.handledby = handledby;
        this.manager = manager;
    }

    public Reimbursement(int requestid, double amount, String status, String description, String type, int handledby, Employee employee, Manager manager) {
        this.requestid = requestid;
        this.amount = amount;
        this.status = status;
        this.description = description;
        this.type = type;
        this.handledby = handledby;
        this.employee = employee;
        this.manager = manager;
    }

    public Reimbursement(double amount, String status, String description) {
        this.amount = amount;
        this.status = status;
        this.description = description;
    }

    public Reimbursement(String status, int handledby, Manager manager) {
        this.status = status;
        this.handledby = handledby;
        this.manager = manager;
    }

    public Reimbursement(String status, Manager manager) {
        this.status = status;
        this.manager = manager;
    }

    public Reimbursement(String status) {
        this.status = status;
    }

    public int getRequestid() {
        return requestid;
    }

    public void setRequestid(int requestid) {
        this.requestid = requestid;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHandledby() {
        return handledby;
    }

    public void setHandledby(int handledby) {
        this.handledby = handledby;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "ticketid=" + requestid +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", type ='" + type +
                ", handledby=" + handledby +
                ", employee=" + employee +
                ", manager=" + manager +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reimbursement that = (Reimbursement) o;
        return requestid == that.requestid && Double.compare(that.amount, amount) == 0 && handledby == that.handledby && Objects.equals(status, that.status) && Objects.equals(description, that.description) && Objects.equals(type, that.type) && Objects.equals(employee, that.employee) && Objects.equals(manager, that.manager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestid, amount, status, description, type, handledby, employee, manager);
    }
}
