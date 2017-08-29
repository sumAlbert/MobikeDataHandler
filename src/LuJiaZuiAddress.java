import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LuJiaZuiAddress {
    public static void main(String[] args){
        try{
            Driver driver=new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/ofo?useUnicode=true&characterEncoding=utf8&useSSL=true","root","123aaaaaa");
            Statement statement=connection.createStatement();
            String sql_fix="insert into lujiazui_address (lng,lat) values (";
            Double start_lng=121.451;
            Double start_lat=31.224;
            Double stop_lng=121.532;
            Double stop_lat=31.245;
            Double inteval=0.001;
            for(int i=0;i<87;i++){
                Double lng=start_lng+inteval*i;
                for(int j=0;j<33;j++){
                    Double lat=start_lat+inteval*j;
                    String lng_str=(String.valueOf(lng)+"00000").substring(0,7);
                    String lat_str=(String.valueOf(lat)+"00000").substring(0,6);
                    String sql=sql_fix+lng_str+","+lat_str+")";
                    System.out.println(sql);
                    statement.execute(sql);
                }
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
