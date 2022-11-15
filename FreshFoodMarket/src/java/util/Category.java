/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Dinh Nam
 */
public class Category {

    private String cateID, cateName;

    public Category() {
    }

    public Category(String cateID) {
        this.cateID = cateID;
    }

    public Category(String cateID, String cateName) {
        this.cateID = cateID;
        this.cateName = cateName;
    }

    public String getCateID() {
        return cateID;
    }

    public void setCateID(String cateID) {
        this.cateID = cateID;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    @Override
    public String toString() {
        return "Category{" + "cateID=" + cateID + ", cateName=" + cateName + '}';
    }

}
