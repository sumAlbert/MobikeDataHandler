import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HeatMap {
    public static void main(String[] args){
        MysqlHandler mysqlHandler=new MysqlHandler();
        Connection connection=mysqlHandler.getConnection();
        String sql="select * from putuo_mobike_address4 where save_time ='2017-08-27 09:14:01'";
        File file=new File("C:\\Users\\98267\\IdeaProjects\\spider2\\src\\Data\\heatmap2\\putuo_heatmap2_1708270914.txt");
        try {
            PrintStream printStream=new PrintStream(new FileOutputStream(file));
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                String distX=resultSet.getString("distX");
                String distY=resultSet.getString("distY");
                String result="{\"lng\":"+distX.substring(0,13)+",\"lat\":"+distY.substring(0,12)+",\"count\":1},\n";
                printStream.append(result);
            }
            printStream.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
