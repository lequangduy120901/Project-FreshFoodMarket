/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Dinh Nam
 */
public class Slider {

    private String slideID;
    private String slideImage, title, description, backlink;
    private boolean status;
    private String notes;

    public Slider() {
    }

    public Slider(String sliderID, String sliImage, String title, String description, String backlink, boolean status, String notes) {
        this.slideID = sliderID;
        this.slideImage = sliImage;
        this.title = title;
        this.description = description;
        this.backlink = backlink;
        this.status = status;
        this.notes = notes;
    }

    public String getSlideID() {
        return slideID;
    }

    public void setSlideID(String slideID) {
        this.slideID = slideID;
    }

    public String getSlideImage() {
        return slideImage;
    }

    public void setSlideImage(String slideImage) {
        this.slideImage = slideImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBacklink() {
        return backlink;
    }

    public void setBacklink(String backlink) {
        this.backlink = backlink;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Slider{" + "slideID=" + slideID + ", slideImage=" + slideImage + ", title=" + title + ", description=" + description + ", backlink=" + backlink + ", status=" + status + ", notes=" + notes + '}';
    }

}
