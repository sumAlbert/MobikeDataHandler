import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

public class PutuoSubwayAddress {
    public static void main(String[] args){
        try{
            File file=new File("C:\\Users\\98267\\IdeaProjects\\spider2\\src\\Data\\subway\\subway1.txt");
            FileInputStream fileInputStream=new FileInputStream(file);
            InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream,"utf8");
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String all="";
            String line=bufferedReader.readLine();
            while(line!=null){
                all=all+line;
                line=bufferedReader.readLine();
            }
            Driver driver=new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/ofo?useUnicode=true&characterEncoding=utf8&useSSL=true","root","123aaaaaa");
            Statement statement=connection.createStatement();
            String[] subways=all.split(";");
            for(int i=0;i<subways.length;i++){
                String[] subway_info=subways[i].split(",");
                String sql="insert into putuo_subway_address (lng,lat,name) values (\'"+subway_info[1]+"\',\'"+subway_info[2]+"\',\'"+subway_info[0]+"\')";
                statement.execute(sql);
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
