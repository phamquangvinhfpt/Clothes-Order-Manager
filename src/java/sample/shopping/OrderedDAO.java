package sample.shopping;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import sample.util.DBUtils;

public class OrderedDAO implements Serializable {

    private static ArrayList<String> getOrders()
            throws ClassNotFoundException, SQLException, NamingException {
        ArrayList<String> orderIDs = new ArrayList<>();
        String SQLQuery = "SELECT orderID "
                + "FROM tblOrder ";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareCall(SQLQuery);
            rs = ps.executeQuery();
            while (rs.next()) {
                String str = rs.getString(1);
                orderIDs.add(str);
            }
        } finally {

            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return orderIDs;
    }

    private static String generateOrderID() {
        ArrayList<String> orderIDs = null;
        try {
            orderIDs = getOrders();
        } catch (ClassNotFoundException | SQLException | NamingException ex) {
        }
        ArrayList<Integer> OrderIDValues = new ArrayList<>();
        for (String o : orderIDs) {
            Integer x = null;
            try {
                x = Integer.parseInt(o.substring(1));
            } catch (NumberFormatException ex) {
            }
            if (x != null) {
                OrderIDValues.add(x);
            }
        }
        int returnvalue = 1;
        boolean flag;
        do {
            flag = false;
            for (Integer i : OrderIDValues) {
                if (i == returnvalue) {
                    returnvalue++;
                    flag = true;
                }
            }

        } while (flag);
        String stringTransform = Integer.toString(returnvalue);
        if (stringTransform.length() > 3) {
            return null;
        }
        int numberof0 = 3 - stringTransform.length();
        if (numberof0 > 0) {
            for (int i = 1; i <= numberof0; i++) {
                stringTransform = "0" + stringTransform;
            }
        }
        stringTransform = "P" + stringTransform;
        return stringTransform;
    }

    public static int TotalPayment(Cart cart) throws NamingException, ClassNotFoundException {
        int total = 0;
        for (String key : cart.getCart().keySet()) {

            Clothes tea = null;
            try {
                tea = ClothesDAO.getWatch(key);
            } catch (SQLException ex) {
            }
            total += tea.getPrice() * cart.getCart().get(key);
        }
        return total;
    }

    public static String addOrder(String Username, int total)
            throws ClassNotFoundException, SQLException, NamingException {

        String OrderID = generateOrderID();
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);

        String SQLQuery = "INSERT INTO tblOrder(OrderID , userID, date, total) "
                + "VALUES (?,?,?,?);";

        Boolean flag = false;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareCall(SQLQuery);
            ps.setString(1, OrderID);
            ps.setString(2, Username);
            ps.setDate(3, date);

            ps.setInt(4, total);
            final int AffectedRow = ps.executeUpdate();
            if (AffectedRow > 0) {
                flag = true;
            }
        } finally {

            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        if (flag) {
            return OrderID;
        } else {
            return null;
        }
    }
}
