import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class PutuoBlockMap {
    public static void main(String[] args){
        int count=0;
        MysqlHandler mysqlHandler=new MysqlHandler();
        Connection connection=mysqlHandler.getConnection();
        String sql="select * from putuo_mobike_address2 where save_time = '2017-08-13 02:00:00'";
        Map<String,Integer> map=new HashMap<>();
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                StringBuffer stringBuffer=new StringBuffer(0);
                Logger logger=Logger.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());
                String lng_str=lngHash(resultSet.getString("distX"));
                String lat_str=latHash(resultSet.getString("distY"));
                stringBuffer.append(lng_str);
                stringBuffer.append(lat_str);
                String key=stringBuffer.toString();
                if(map.containsKey(key)){
                    Integer value_Int=map.get(key);
                    int value_int=value_Int.intValue();
                    value_int+=1;
                    map.put(key,new Integer(value_int+1));
                }
                else{
                    map.put(key,new Integer(1));
                }
                count++;
            }
            resultSet.close();
            statement.close();
            connection.close();
            System.out.println(count);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
    private static String lngHash(String lng){
        String result="";
        String key_str=lng.substring(4,6);
        int key_int=Integer.parseInt(key_str)+32;
        char key_char=(char)key_int;
        result+=key_char;
        return result;
    }
    private static String latHash(String lat){
        String result="";
        String key_str=lat.substring(3,5);
        int key_int=Integer.parseInt(key_str)+26;
        char key_char=(char)key_int;
        result+=key_char;
        return result;
    }
}