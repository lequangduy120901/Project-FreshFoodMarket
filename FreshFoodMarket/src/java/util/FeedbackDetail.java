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
public class FeedbackDetail {

    private Feedback feedback;
    private Product product;
    private int rated;
    private String content;
    private Date dateCreate;

    public FeedbackDetail() {
    }

    public FeedbackDetail(Feedback feedback, Product product, int rated, String content, Date dateCreate) {
        this.feedback = feedback;
        this.product = product;
        this.rated = rated;
        this.content = content;
        this.dateCreate = dateCreate;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
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
        return "FeedbackDetail{" + "feedback=" + feedback + ", product=" + product + ", rated=" + rated + ", content=" + content + ", dateCreate=" + dateCreate + '}';
    }

}
