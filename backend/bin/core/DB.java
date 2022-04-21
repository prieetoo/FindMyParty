package core;

import java.sql.*;

public class DB {
    Connection connection;
    Statement statement;

    public DB(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            this.connection = DriverManager.getConnection("BD","USER","PASS");
            this.statement = this.connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ResultSet execute(String operation){
        ResultSet rs = null;
        try {
            rs = this.statement.executeQuery("select * from emp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
