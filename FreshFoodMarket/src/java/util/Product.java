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
public class Product {

    private String proID;
    private Category category;
    private String proName;
    private double price;
    private String type;
    private int quantity;
    private String image, description;
    private int rated;
    private Date updateDate;

    public Product() {
    }

    public Product(String proID, Category category, String proName, double price, String type, int quantity, String image, String description, int rated, Date updateDate) {
        this.proID = proID;
        this.category = category;
        this.proName = proName;
        this.price = price;
        this.type = type;
        this.quantity = quantity;
        this.image = image;
        this.description = description;
        this.rated = rated;
        this.updateDate = updateDate;
    }
    
    public Product(String proID) {
        this.proID = proID;
    }
    
    public Product(String proID, String proName) {
        this.proID = proID;
        this.proName = proName;
    }

    public String getProID() {
        return proID;
    }

    public void setProID(String proID) {
        this.proID = proID;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRated() {
        return rated;
    }

    public void setRated(int rated) {
        this.rated = rated;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Product{" + "proID=" + proID + ", category=" + category + ", proName=" + proName + ", price=" + price + ", type=" + type + ", quantity=" + quantity + ", image=" + image + ", description=" + description + ", rated=" + rated + ", updateDate=" + updateDate + '}';
    }

}
