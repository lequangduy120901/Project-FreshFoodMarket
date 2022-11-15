/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Date;

/**
 *
 * @author Dinh Nam
 */
public class Feedback {

    private int feID;
    private Customer customer;
    private Product product;
    private int rated;
    private String content;
    private Date dateCreate;
    

    public Feedback() {}

    public Feedback(int feID, Customer customer, Product product, int rated, String content, Date dateCreated) {
        this.feID = feID;
        this.customer = customer;
        this.product = product;
        this.rated = rated;
        this.content = content;
        this.dateCreate = dateCreated;
    }

    public int getFeID() {
        return feID;
    }

    public void setFeID(int feID) {
        this.feID = feID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getRated() {
        return rated;
    }

    public void setRated(int rated) {
        this.rated = rated;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    @Override
    public String toString() {
        return "Feedback{" + "feID=" + feID + ", customer=" + customer + ", product=" + product + ", rated=" + rated + ", content=" + content + ", dateCreated=" + dateCreate + '}';
    }

    
   
}
