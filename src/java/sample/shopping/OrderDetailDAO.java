package sample.shopping;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
}
