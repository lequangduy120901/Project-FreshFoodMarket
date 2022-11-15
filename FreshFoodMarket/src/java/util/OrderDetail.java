/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Dinh Nam
 */
public class OrderDetail {

    private Product product;
    private Order order;
    private int quantity;
    private double total;

    public OrderDetail() {
    }

    public OrderDetail(Product product, Order order, int quantity, double total) {
        this.product = product;
        this.order = order;
        this.quantity = quantity;
        this.total = total;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "product=" + product + ", order=" + order + ", quantity=" + quantity + ", total=" + total + '}';
    }

}
