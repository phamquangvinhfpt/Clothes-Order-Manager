
package sample.shopping;

import java.util.Date;

public class Order {
    private String orderID;
    private String userID;
    private Date date;
    private double total;

    public Order() {
    }

    public Order(String orderID, String userID, Date date, double total) {
        this.orderID = orderID;
        this.userID = userID;
        this.date = date;
        this.total = total;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", userID=" + userID + ", date=" + date + ", total=" + total + '}';
    }
    
    
}
