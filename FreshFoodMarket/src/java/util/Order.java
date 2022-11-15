/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Date;

/**
 *
 * @author Dinh Nam
 */
public class Order {

    private String orderID;
    private Customer customer;
    private Date dateCreate;
    private double total;
    private int status;

    public Order() {
    }

    public Order(String orderID, Customer customer, Date dateCreate, double total, int status) {
        this.orderID = orderID;
        this.customer = customer;
        this.dateCreate = dateCreate;
        this.total = total;
        this.status = status;
    }

    public Order(String orderID, Customer customer, double total, int status) {
        this.orderID = orderID;
        this.customer = customer;
        this.total = total;
        this.status = status;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", customer=" + customer + ", dateCreate=" + dateCreate + ", total=" + total + ", status=" + status + '}';
    }   

}
