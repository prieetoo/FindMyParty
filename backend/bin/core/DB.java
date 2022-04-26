package core;

import java.sql.*;

public final class DB {
    private static Connection connection;
    private static Statement statement;
    private static DB instance;

    public DB(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/FMP","root","root");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String operation){
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(operation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public boolean executeUpdate(String operation){
        int rs = 0;
        try {
            rs = statement.executeUpdate(operation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs > 0;
    }

    public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
        }
        return instance;
    }
}
