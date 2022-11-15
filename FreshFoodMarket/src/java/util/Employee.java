/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Dinh Nam
 */
public class Employee {

    private String emID;
    private Account account;
    private String emName;
    private boolean gender;
    private String email, emPhone, emAddress, emImage;

    public Employee() {
    }

    public Employee(String emID, Account account, String emName, boolean gender, String email, String emPhone, String emAddress, String emImage) {
        this.emID = emID;
        this.account = account;
        this.emName = emName;
        this.gender = gender;
        this.email = email;
        this.emPhone = emPhone;
        this.emAddress = emAddress;
        this.emImage = emImage;
    }

    public String getEmID() {
        return emID;
    }

    public void setEmID(String emID) {
        this.emID = emID;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getEmName() {
        return emName;
    }

    public void setEmName(String emName) {
        this.emName = emName;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmPhone() {
        return emPhone;
    }

    public void setEmPhone(String emPhone) {
        this.emPhone = emPhone;
    }

    public String getEmAddress() {
        return emAddress;
    }

    public void setEmAddress(String emAddress) {
        this.emAddress = emAddress;
    }

    public String getEmImage() {
        return emImage;
    }

    public void setEmImage(String emImage) {
        this.emImage = emImage;
    }

    @Override
    public String toString() {
        return "Employee{" + "emID=" + emID + ", account=" + account + ", emName=" + emName + ", gender=" + gender + ", email=" + email + ", emPhone=" + emPhone + ", emAddress=" + emAddress + ", emImage=" + emImage + '}';
    }

}
