/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.extra;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 *
 * @author zedqu
 */
public class AccessPermission {
    final private int guest = -1;
    final private int cus = 0;
    final private int sale = 1;
    final private int admin = 2;
    private boolean guestAllowed,cusAllowed,saleAllowed,adminAllowed;
    Dictionary<Integer, Boolean> dictionary;

    public AccessPermission() {
        dictionary = new Hashtable<>();
        dictionary.put(guest, guestAllowed);
        dictionary.put(cus, cusAllowed);
        dictionary.put(sale, saleAllowed);
        dictionary.put(admin, adminAllowed);
    }

    public AccessPermission(boolean guestAllowed, boolean cusAllowed, boolean saleAllowed, boolean adminAllowed) {
        dictionary = new Hashtable<>();
        dictionary.put(guest, guestAllowed);
        dictionary.put(cus, cusAllowed);
        dictionary.put(sale, saleAllowed);
        dictionary.put(admin, adminAllowed);
    }

    public int getGuest() {
        return guest;
    }

    public int getCus() {
        return cus;
    }

    public int getSale() {
        return sale;
    }

    public int getAdmin() {
        return admin;
    }

    public Dictionary<Integer, Boolean> getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary<Integer, Boolean> dictionary) {
        this.dictionary = dictionary;
    }

    public boolean isGuestAllowed() {
        return guestAllowed;
    }

    public void setGuestAllowed(boolean guestAllowed) {
        dictionary.remove(guest);
        dictionary.put(guest, guestAllowed);
    }

    public boolean isCusAllowed() {
        return cusAllowed;
    }

    public void setCusAllowed(boolean cusAllowed) {
        dictionary.remove(cus);
        dictionary.put(cus, cusAllowed);
    }

    public boolean isSaleAllowed() {
        return saleAllowed;
    }

    public void setSaleAllowed(boolean saleAllowed) {
        dictionary.remove(sale);
        dictionary.put(sale, saleAllowed);;
    }

    public boolean isAdminAllowed() {
        return adminAllowed;
    }

    public void setAdminAllowed(boolean adminAllowed) {
        dictionary.remove(admin);
        dictionary.put(admin, adminAllowed);
    }
    
    
}
