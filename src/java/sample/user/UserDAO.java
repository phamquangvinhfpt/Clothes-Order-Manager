package sample.user;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sample.util.DBUtils;

/**
 *
 * @author Thien
 */
public class UserDAO {

    private static final String LOGIN = "SELECT name, phone, address, email, roleID FROM tblUser WHERE userID = ? AND password = ?";
    private static final String SEARCH = "SELECT userID, name, phone, address, email, roleID FROM tblUser WHERE name like ?";
    private static final String DELETE = "DELETE tblUser WHERE userID = ?";
    private static final String UPDATE = "UPDATE tblUser SET name = ?, roleID = ?, phone = ?, address = ?, email = ?, password = ? WHERE userID = ?";
    private static final String CHECK = "SELECT userID FROM tblUser WHERE userID = ?";
    private static final String INSERT = "INSERT INTO tblUser (userID, name, phone, address, email, roleID, password) VALUES(?,?,?,?,?,?,?)";

    public static UserDTO checkLogin(String userID, String password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                ptm = conn.prepareStatement(LOGIN);
                ptm.setString(1, userID);
                ptm.setString(2, password);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("name");
                    String roleID = rs.getString("roleID");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    String email = rs.getString("email");
                    user = new UserDTO(userID, fullName, phone, address, email, roleID, password);
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
        return user;
    }

    public boolean checkDuplicate(String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK);
                ptm.setString(1, userID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {

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
        return check;
    }

    public List<UserDTO> getListUser(String search) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                ptm = conn.prepareStatement(SEARCH);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("name");
                    String roleID = rs.getString("roleID");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");
                    String address = rs.getString("address");
                    list.add(new UserDTO(userID, fullName, phone, address, email, roleID, "**"));
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

    public boolean deleteUser(String userID) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setString(1, userID);
                int value = ptm.executeUpdate();
                result = value > 0;
            }
        } catch (SQLException | NamingException e) {

        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public boolean updateUser(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, user.getFullName());
                ptm.setString(2, user.getRoleID());

                ptm.setString(3, user.getPhone());
                ptm.setString(4, user.getAddress());
                ptm.setString(5, user.getEmail());
                ptm.setString(6, user.getPassword());
                ptm.setString(7, user.getUserID());
                check = ptm.executeUpdate() > 0;
            }
        } catch (SQLException | NamingException e) {
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public static boolean insert(String userID, String fullName, String roleID, String phone, String address, String email, String password) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT);
                ptm.setString(1, userID);
                ptm.setString(2, fullName);
                ptm.setString(3, phone);
                ptm.setString(4, address);
                ptm.setString(5, email);
                ptm.setString(6, roleID);
                ptm.setString(7, password);
                check = ptm.executeUpdate() > 0;
            }
        } catch (SQLException | NamingException e) {
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public List<UserDTO> getAllUser() throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String query = "SELECT userID, name, phone, address, email, roleID, password FROM tblUser";
                ptm = conn.prepareStatement(query);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    list.add(new UserDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
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

    public static void main(String[] args) throws SQLException {
        UserDAO dao = new UserDAO();
        List<UserDTO> user = dao.getAllUser();

        user.forEach((us) -> {
            System.out.println(us);
        });
    }

    public static String getUsername(UserDTO user)
            throws SQLException, ClassNotFoundException, NamingException {
        String SQL = "SELECT username FROM tblUser WHERE phone = ?";
        String username = null;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                pre.setString(1, user.getPhone());
                res = pre.executeQuery();
                while (res.next()) {
                    String users = res.getString("username");
                    username = users;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }

            if (pre != null) {
                pre.close();
            }

            if (res != null) {
                res.close();
            }

        }
        return username;
    }

    public static UserDTO getUserUsername(String username)
            throws SQLException, ClassNotFoundException, NamingException {
        String SQL = "SELECT * FROM tblGoogleAccount WHERE username = ?";
        UserDTO user = null;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                pre.setString(1, username);
                res = pre.executeQuery();
                while (res.next()) {
                    String users = res.getString("username");
                    String email = res.getString("email");
                    String password = res.getString("password");
                    String firstName = res.getString("firstName");
                    String lastName = res.getString("lastName");
                    String picture = res.getString("picture");
                    String role = res.getString("role");
                    Date DOB = res.getDate("DOB");
                    String address = res.getString("address");
                    String organization = res.getString("organization");
                    String phoneNumber = res.getString("phone");
                    boolean status = res.getBoolean("status");
                    user = new UserDTO(users, role, password, phoneNumber, address, email, firstName, lastName, picture, DOB, organization, status);
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }

            if (pre != null) {
                pre.close();
            }

            if (res != null) {
                res.close();
            }

        }
        return user;
    }

    public static UserDTO getUserUsernamePassword(String username_, String password_)
            throws SQLException, ClassNotFoundException, NamingException {
        String SQL = "SELECT * FROM tblGoogleAccount WHERE username = ? AND password = ?";
        UserDTO user = null;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                pre.setString(1, username_);
                pre.setString(2, password_);
                res = pre.executeQuery();
                while (res.next()) {
                    String username = res.getString("username");
                    String email = res.getString("email");
                    String password = res.getString("password");
                    String firstName = res.getString("firstName");
                    String lastName = res.getString("lastName");
                    String role = res.getString("role");
                    String picture = res.getString("picture");
                    Date DOB = res.getDate("DOB");
                    String organization = res.getString("organization");
                    String address = res.getString("address");
                    String phoneNumber = res.getString("phoneNumber");
                    boolean status = res.getBoolean("status");
                    return new UserDTO(username, role, password, phoneNumber, address, email, firstName, lastName, picture, DOB, organization, status);
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }

            if (pre != null) {
                pre.close();
            }

            if (res != null) {
                res.close();
            }

        }
        return user;
    }

    public static List<UserDTO> getUsers()
            throws SQLException, ClassNotFoundException, NamingException {
        String SQL = "SELECT * FROM tblGoogleAccount";
        List<UserDTO> users = null;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                res = pre.executeQuery();
                while (res.next()) {
                    String username = res.getString("username");
                    String email = res.getString("email");
                    String password = res.getString("password");
                    String firstName = res.getString("firstName");
                    String lastName = res.getString("lastName");
                    String role = res.getString("role");
                    String picture = res.getString("picture");
                    Date DOB = res.getDate("DOB");
                    String organization = res.getString("organization");
                    String address = res.getString("address");
                    String phoneNumber = res.getString("phoneNumber");
                    boolean status = res.getBoolean("status");
                    if (users == null) {
                        users = new ArrayList<>();
                    }
                    users.add(new UserDTO(username, role, password, phoneNumber, address, email, firstName, lastName, picture, DOB, organization, status));
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }

            if (pre != null) {
                pre.close();
            }

            if (res != null) {
                res.close();
            }

        }
        return users;
    }

    public static boolean addUser(UserDTO user)
            throws SQLException, ClassNotFoundException, NamingException {
        String SQL = "INSERT INTO tblGoogleAccount (username, email, password, firstname, lastname, picture, role, DOB, address, organization, phone, status)"
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        boolean flag = false;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);
                pre.setString(1, user.getUserID());
                pre.setString(2, user.getEmail());
                pre.setString(3, user.getPassword());
                pre.setString(4, user.getFirstName());
                pre.setString(5, user.getLastName());
                pre.setString(6, user.getPicture());
                pre.setString(7, user.getRoleID());
                pre.setDate(8, user.getDOB());
                pre.setString(9, user.getAddress());
                pre.setString(10, user.getOrganization());
                pre.setString(11, user.getPhone());
                pre.setBoolean(12, user.isStatus());

                int affectedRow = pre.executeUpdate();
                if (affectedRow > 0) {
                    flag = true;
                }

            }
        } catch (SQLException e) {
        } finally {
            if (con != null) {
                con.close();
            }

            if (pre != null) {
                pre.close();
            }

            if (res != null) {
                res.close();
            }

        }
        return flag;
    }

    public static boolean updateUser(String username_, UserDTO user) throws SQLException, ClassNotFoundException, NamingException {
        String SQL = "UPDATE tblGoogleAccount SET email = ?, password = ?, firstname = ?, lastname = ?, "
                + "picture = ?, role = ?, DOB = ?, address = ?, "
                + "organization = ?, phone = ?, status = ? WHERE username = ?";
        boolean flag = false;
        Connection con = null;
        PreparedStatement pre = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                pre = con.prepareStatement(SQL);

                pre.setString(1, user.getEmail());
                pre.setString(2, user.getPassword());
                pre.setString(3, user.getFirstName());
                pre.setString(4, user.getLastName());
                pre.setString(5, user.getPicture());
                pre.setString(6, user.getRoleID());
                pre.setDate(7, user.getDOB());
                pre.setString(8, user.getAddress());
                pre.setString(9, user.getOrganization());
                pre.setString(10, user.getPhone());
                pre.setBoolean(11, user.isStatus());
                pre.setString(12, user.getUserID());

                int affectRow = pre.executeUpdate();
                if (affectRow > 0) {
                    flag = true;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (pre != null) {
                pre.close();
            }

        }
        return flag;
    }

}
