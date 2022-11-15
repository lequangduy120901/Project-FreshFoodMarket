/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Dinh Nam
 */
public class Account {
    private int accID;
    private String username, password;
    private int role;

    public Account() {
    }

    public Account(int accID, String username, String password, int role) {
        this.accID = accID;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Account(int accID, int role) {
        this.accID = accID;
        this.role = role;
    }


    public int getAccID() {
        return accID;
    }

    public void setAccID(int accID) {
        this.accID = accID;
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Account{" + "accID=" + accID + ", username=" + username + ", password=" + password + ", role=" + role + '}';
    }
}
