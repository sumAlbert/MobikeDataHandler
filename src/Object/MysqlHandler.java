package Object;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class MysqlHandler {
    private final String DB_URL="jdbc:mysql://localhost:3306/ofo?useUnicode=true&characterEncoding=UTF-8&useSSL=true";
    private final String USER="root";
    private final String PW="123aaaaaa";
    private Connection connection;
    public MysqlHandler(){
        try {
            Driver driver=new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection=DriverManager.getConnection(DB_URL,USER,PW);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
    public Connection getConnection() {
        return connection;
    }
}
