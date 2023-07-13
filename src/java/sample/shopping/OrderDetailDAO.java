package sample.shopping;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sample.util.DBUtils;

public class OrderDetailDAO implements Serializable {

    public static boolean addOrderDetails(String OrderID, Cart cart)
            throws SQLException, ClassNotFoundException, NamingException {
        String SQLQuery = "INSERT INTO "
                + "tblOrderDetail(orderID, productID, price, quantity, detailID) "
                + "VALUES (?,?,?,?,?)";

        boolean flag = true;
        Connection conn = null;
        PreparedStatement ps = null;
        Clothes watch = null;
        try {
            conn = DBUtils.getConnection();
            for (String key : cart.getCart().keySet()) {

                try {
                    watch = ClothesDAO.getWatch(key);
                } catch (SQLException ex) {
                }
                ps = conn.prepareCall(SQLQuery);

                ps.setString(1, OrderID);
                ps.setString(2, key);
                ps.setDouble(3, watch.getPrice() * cart.getCart().get(key));
                ps.setInt(4, cart.getCart().get(key));
                ps.setString(5, key);
                final int AffectedRow = ps.executeUpdate();
                if (AffectedRow == 0) {
                    flag = false;
                }
            }

        } finally {

            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return flag;
    }

    public List<OrderDetail> getListOrderDetail(String id) throws ClassNotFoundException, SQLException {
        List<OrderDetail> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                ptm = conn.prepareStatement("SELECT orderID, productID, price, quantity FROM tblOrderDetail");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String orderID = rs.getString("orderID");
                    //String detailID = rs.getString("detailID");
                    String productID = rs.getString("productID");
                    double price = rs.getDouble("price");
                    int qty = rs.getInt("quantity");
                    list.add(new OrderDetail(orderID, productID, qty, price));
                }
            }
        } catch (SQLException | NamingException e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
}
