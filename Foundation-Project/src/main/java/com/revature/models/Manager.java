package com.revature.models;

import java.util.Objects;

public class Manager {

    private int managerId;

    private String first;

    private String last;

    private String email;

    private String username;

    private String password;

    public Manager(int managerId, String first, String last, String email, String username, String password) {
        this.managerId = managerId;
        this.first = first;
        this.last = last;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Manager() {
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "managerId=" + managerId +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager manager = (Manager) o;
        return managerId == manager.managerId && Objects.equals(first, manager.first) && Objects.equals(last, manager.last) && Objects.equals(email, manager.email) && Objects.equals(username, manager.username) && Objects.equals(password, manager.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(managerId, first, last, email, username, password);
    }
}
