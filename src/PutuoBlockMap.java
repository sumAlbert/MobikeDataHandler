import java.sql.Connection;

public class PutuoBlockMap {
    public static void main(String[] args){
        MysqlHandler mysqlHandler=new MysqlHandler();
        Connection connection=mysqlHandler.getConnection();
        String sql="select * from putuo_mobike_address2 where save_time = '2017-08-14 07:00:00'";
    }
}
