/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Date;

/**
 *
 * @author zedqu
 */
public class Customer {
    

    private String cusID;
    private Account account;
    private String cusName;
    private boolean gender;
    private String email, cusPhone, cusAddress, cusImage, updateBy;
    private Date updateDate;

    public Customer() {
    }

    public Customer(String cusID, String cusName) {
        this.cusID = cusID;
        this.cusName = cusName;
    }
    

    public Customer(String cusID, Account account, String cusName, boolean gender, String email, String cusPhone, String cusAddress, String cusImage, String updateBy, Date updateDate) {
        this.cusID = cusID;
        this.account = account;
        this.cusName = cusName;
        this.gender = gender;
        this.email = email;
        this.cusPhone = cusPhone;
        this.cusAddress = cusAddress;
        this.cusImage = cusImage;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
    }

    public Customer(String cusID, String cusName, String email, String cusPhone, String cusAddress) {
        this.cusID = cusID;
        this.cusName = cusName;
        this.email = email;
        this.cusPhone = cusPhone;
        this.cusAddress = cusAddress;
    }
    
    public Customer(String cusID) {
        this.cusID = cusID;
    }
    
    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
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

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public String getCusAddress() {
        return cusAddress;
    }

    public void setCusAddress(String cusAddress) {
        this.cusAddress = cusAddress;
    }

    public String getCusImage() {
        return cusImage;
    }

    public void setCusImage(String cusImage) {
        this.cusImage = cusImage;
    }

    public String getUpddateBy() {
        return updateBy;
    }

    public void setUpddateBy(String upddateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Customer{" + "cusID=" + cusID + ", account=" + account + ", cusName=" + cusName + ", gender=" + gender + ", email=" + email + ", cusPhone=" + cusPhone + ", cusAddress=" + cusAddress + ", cusImage=" + cusImage + ", upddateBy=" + updateBy + ", updateDate=" + updateDate + '}';
    }


}
