/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.NamingException;

public class DBUtils {
    
    public static Connection getConnection() throws NamingException, SQLException, ClassNotFoundException{
//        Connection conn = null;
//        Context context = new InitialContext();
//        Context end = (Context) context.lookup("java:comp/env");
//        DataSource ds = (DataSource) end.lookup("DBConn");
//        conn = ds.getConnection();
//        return conn;
        Connection cn = null;
        String IP = "localhost";
        String instanceName = "localhost";
        String port = "1433";
        String uid = "sa";
        String pwd = "12345";
        String db = "Shopping";
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://" + IP + "\\" + instanceName + ":" + port
                + ";databasename=" + db + ";user=" + uid + ";password=" + pwd;
        Class.forName(driver);
        cn = DriverManager.getConnection(url);
        return cn;
    }
    
}
