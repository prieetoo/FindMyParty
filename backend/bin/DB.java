import java.sql.*;

public class DB {
    Connection connection;
    Statement statement;

    public DB(){
        this.connection = DriverManager.getConnection("BD","USER","PASS");
        this.statement = this.connection.createStatement();
    }
    public ResultSet execute(String operation){
        return this.statement.executeQuery("select * from emp");
    }
}
