package core;

import java.sql.*;

public class DB {
    static Connection connection;
    static Statement statement;


    public DB(){

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("BD","USER","PASS");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static ResultSet executeQuery(String operation){
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(operation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static boolean executeUpdate(String operation){
        int rs = 0;
        try {
            rs = statement.executeUpdate(operation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs > 0;
    }
}
