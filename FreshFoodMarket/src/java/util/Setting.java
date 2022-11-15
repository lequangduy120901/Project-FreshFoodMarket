/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Dinh Nam
 */
public class Setting {

    private int setID;
    private String type, value, order, description;
    private boolean status;

    public Setting() {
    }

    public Setting(int setID, String type, String value, String order, String description, boolean status) {
        this.setID = setID;
        this.type = type;
        this.value = value;
        this.order = order;
        this.description = description;
        this.status = status;
    }

    public int getSetID() {
        return setID;
    }

    public void setSetID(int setID) {
        this.setID = setID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Setting{" + "setID=" + setID + ", type=" + type + ", value=" + value + ", order=" + order + ", description=" + description + ", status=" + status + '}';
    }

}
